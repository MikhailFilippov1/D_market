angular.module('market-front').controller('orderItemController', function ($scope, $http, $routeParams, $location){
    const contextPath = 'http://localhost:5555/core/';
    let currentPageIndex = 1;

    $scope.loadOrderItems = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http ({
            url: contextPath + 'api/V1/orderItem/' + $routeParams.orderId,
            method: 'GET',
            params: {
                p: pageIndex
                }
        }).then(function(response){
            $scope.orderItemPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.orderItemPage.totalPages);
//            $scope.mmm = $scope.orderItemPage.orderId;
            $scope.mmm = $routeParams.orderId;
            console.log('Информация по  заказу №' + $scope.mmm);
            });
    }

     $scope.generatePagesIndexes = function(startPage, endPage){
                let arr = [];
                    for(let i = startPage; i < endPage + 1; i++){
                        arr.push(i);
                    }
                    return arr;
        }

     $scope.navToOrderPage = function(){
                    $location.path('/order');
                }

//     $scope.navToInfoPage = function(productId){
//          console.log('Переход в окно информации');
//             $location.path('/info/' + productId);
//          }

//     $scope.deleteOrderItem = function (order){
//             $http({
//                 url: contextPath + 'api/V1/orders/clear/' + order.id,
//                 method: 'GET',
//
//             }).then(function (response){
//                 $scope.loadOrders(currentPageIndex);
//             });
//     }
//
//    $scope.clear = function (){
//             $http({
//                 url: contextPath + 'api/V1/orders',
//                 method: 'DELETE',
//             }).then(function (response){
//                 $scope.loadOrders();
//             });
//         }

     $scope.loadOrderItems();
});