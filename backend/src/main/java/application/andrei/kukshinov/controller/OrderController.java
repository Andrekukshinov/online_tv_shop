package application.andrei.kukshinov.controller;

import application.andrei.kukshinov.additionalEnumsAndClasses.ComparingTvInOrderController;
import application.andrei.kukshinov.entitiy.*;
import application.andrei.kukshinov.repository.JoinOrderTvRepository;
import application.andrei.kukshinov.repository.OrderRepository;
import application.andrei.kukshinov.repository.TvRepository;
import application.andrei.kukshinov.repository.WarehouseOfTvsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

//todo deal with objects creation order

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private JoinOrderTvRepository orderTvRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WarehouseOfTvsRepository warehouseRepo;
    @Autowired
    private TvRepository tvRepo;

    public OrderController() {
    }

    public OrderController(
            JoinOrderTvRepository orderTvRepository,
            OrderRepository orderRepository,
            WarehouseOfTvsRepository warehouseRepo,
            TvRepository tvRepo) {
        this.orderTvRepository = orderTvRepository;
        this.orderRepository = orderRepository;
        this.warehouseRepo = warehouseRepo;
        this.tvRepo = tvRepo;
    }
    private List<WarehouseOfTvs> getWarehouseOfTvsCurrentData(Set<JoinOrderTv> tvInOrder) {
        List<Long> tvIds = tvInOrder.stream().map(joinOrderTv -> joinOrderTv.getTv().getId()).collect(Collectors.toList());
        return warehouseRepo.findTvsInOrder(tvIds);
    }

    private Map<TvSet, Long> gettingMapOfSoldTvStatistics(List<JoinOrderTv> orderTv) {
        Map<TvSet, Long> tvMap = new TreeMap<>(ComparingTvInOrderController::compareTv);
        long maxTvId = orderTv.stream().map(JoinOrderTv::getTv).max(ComparingTvInOrderController::compareTv).map(TvSet::getId).orElse(-1L);
        long[] totalQuantityTvInOrder = new long[1];
        TvSet[] container = new TvSet[1];
        LongStream.range(1, maxTvId+1).forEach(
                runner -> {
                            orderTv.stream()
                            .filter(elem -> elem.getTv().getId() == runner)
                            .forEach(joinOrderTv -> {
                                container[0] = joinOrderTv.getTv();
                                totalQuantityTvInOrder[0] += joinOrderTv.getTvQuantity();
                            });
                            if (totalQuantityTvInOrder[0] > 0) {
                                tvMap.put(container[0], totalQuantityTvInOrder[0]);
                                totalQuantityTvInOrder[0] = 0;
                            }
                });
        return tvMap;
    }
    private void setRealTvPrice(Set<JoinOrderTv> OrderWithTvs){
        List<WarehouseOfTvs> cont = getWarehouseOfTvsCurrentData(OrderWithTvs);
        cont.stream().forEach(warehouse ->
                //todo correct logic
                OrderWithTvs.stream().map(JoinOrderTv::getTv)
                        .filter(tvInOrder -> tvInOrder.getId() == warehouse.getTvModel().getId())
                        .forEach(tvInOrder -> tvInOrder.getWarehouse().setPrice(warehouse.getPrice()))
        );
    }


    private void decreasingTvAmountInWarehouse(Set<JoinOrderTv> tvInOrder) {
        List<WarehouseOfTvs> warehouse = getWarehouseOfTvsCurrentData(tvInOrder);
        tvInOrder.stream().forEach(
                joinOrderTv ->
                       warehouse.stream()
                                .filter(warehouseOfTvs ->
                                        warehouseOfTvs.getTvModel().getId() == joinOrderTv.getTv().getId())
                                .forEach(wareTvs -> {
                                    wareTvs.setTvAmount(wareTvs.getTvAmount()-joinOrderTv.getTvQuantity());
                                    int amount = warehouseRepo
                                        .updateTvAmountInWarehouse(joinOrderTv.getTvQuantity(), joinOrderTv.getTv().getId());
                                    assert (amount>0);
                                })
        );
    }



    //todo Response entity
    @PostMapping
    public Order saveNewOrder(@RequestBody Order order) {
        setRealTvPrice(order.getOrderTvs());
        decreasingTvAmountInWarehouse(order.getOrderTvs());
        orderTvRepository.saveAll(order.getOrderTvs());
        return orderRepository.save(order);
    }

    @GetMapping("/all/month")
    public List<Order> getOrdersWithin30Days() {
        long withIn30Days = new Date().getTime() - 2419200000L;
        java.sql.Date sqlDate30DaysAgo = new java.sql.Date(withIn30Days);
        return orderRepository.getAllByCreationDateAfter(sqlDate30DaysAgo);
    }

    @GetMapping("/all/orders")
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @GetMapping("/all/user")
    public List<Order> getAllUserOrders(User user) {
        return orderRepository.getAllByUser_Id(user.getId());
    }
    // provides statistics on sold tvs in last 30 days (if there were any)
    // in a treeMap where the key is tv and value is amount
    @GetMapping("/all/tv_stat")
    public Map<TvSet, Long> getTvStatisticsSoldLast30Days() {
        long withIn30Days = new Date().getTime() - 2419200000L;
        java.sql.Date sqlDate30DaysAgo = new java.sql.Date(withIn30Days);
        java.sql.Date  now = new java.sql.Date(new Date().getTime());
                List<JoinOrderTv> tvs = orderTvRepository.getAllTvAfter(sqlDate30DaysAgo, now);
        return gettingMapOfSoldTvStatistics(tvs);
    }

    public TvRepository getTvRepo() {
        return tvRepo;
    }

    public void setTvRepo(TvRepository tvRepo) {
        this.tvRepo = tvRepo;
    }



    public WarehouseOfTvsRepository getWarehouseRepo() {
        return warehouseRepo;
    }

    public void setWarehouseRepo(WarehouseOfTvsRepository warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public JoinOrderTvRepository getOrderTvRepository() {
        return orderTvRepository;
    }

    @Autowired
    public void setOrderTvRepository(JoinOrderTvRepository orderTvRepository) {
        this.orderTvRepository = orderTvRepository;
    }

    //todo think of possible response entities usages as returning value from methods
}


//
//
//
//
//class TestMethodTvStatistics {
//    private int id;
//
//    public TestMethodTvStatistics(int id) {
//        this.id = id;
//    }
//
//    @Override
//    public String toString() {
//        return "g{" + "id=" + id + '}';
//    }
//
//    public static void main(String[] args) {
//        List<TestMethodTvStatistics> tvList = new ArrayList<>();
//        tvList.add(new TestMethodTvStatistics(0));
////        tvList.add(new TestMethodTvStatistics(1));
////        tvList.add(new TestMethodTvStatistics(1));
//        tvList.add(new TestMethodTvStatistics(1));
////        tvList.add(new TestMethodTvStatistics(2));
////        tvList.add(new TestMethodTvStatistics(3));
////        tvList.add(new TestMethodTvStatistics(3));
//        tvList.add(new TestMethodTvStatistics(3));
//        tvList.add(new TestMethodTvStatistics(3));
//        tvList.add(new TestMethodTvStatistics(3));
//        tvList.add(new TestMethodTvStatistics(3));
//        tvList.add(new TestMethodTvStatistics(4));
//        tvList.add(new TestMethodTvStatistics(5));
//        tvList.add(new TestMethodTvStatistics(6));
//        tvList.add(new TestMethodTvStatistics(8));
//        tvList.add(new TestMethodTvStatistics(99));
//        tvList.add(new TestMethodTvStatistics(6));
//        tvList.add(new TestMethodTvStatistics(7));
//        TestMethodTvStatistics[] gg = new TestMethodTvStatistics[1];
//        Map<TestMethodTvStatistics, Integer> tvMap = new TreeMap<>(Comparator.comparingInt(obj -> obj.id));
//        int maxId = tvList.stream().max(( tv1,  tv2) ->  {
//            if (tv1.id > tv2.id) {
//                return 1;
//            }
//            else if (tv1.id == tv2.id) {
//                return 0;
//            }
//            else return -1;
//        }).map(tv -> tv.id).orElse(-1);
//        IntStream.range(0, maxId + 1).forEach(runner -> {
//            int count = (int) tvList.stream().filter(elem -> elem.id == runner).peek(TestMethodTvStatistics -> gg[0] = TestMethodTvStatistics).count();
//            System.out.println("id = " + runner + " count = " + count);
//            if (count > 0) {
//                tvMap.put(gg[0], count);
//            }
//        });
//        System.out.println(tvMap);
//    }
//
//}
