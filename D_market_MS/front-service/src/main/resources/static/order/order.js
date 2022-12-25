angular.module('market-front').controller('orderController', function ($scope, $http, $location, $localStorage){
    const contextPath = 'http://localhost:5555/core/';
    let currentPageIndex = 1;

    $scope.loadOrders = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http ({
            url: contextPath + 'api/V1/orders',
            method: 'GET',
            params: {
                p: pageIndex
                }
        }).then(function(response){
            $scope.orderPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.orderPage.totalPages);
            $scope.uuu = $localStorage.MarketUser.username;
            });
    }

     $scope.generatePagesIndexes = function(startPage, endPage){
                let arr = [];
                    for(let i = startPage; i < endPage + 1; i++){
                        arr.push(i);
                    }
                    return arr;
        }

     $scope.navToStorePage = function(){
                    $location.path('/store');
                }

     $scope.navToInfoPage = function(order){
          console.log('Переход в окно информации по позициям заказа #' + order.id);
             $location.path('/orderItem/' + order.id);
          }

     $scope.deleteOrder = function (order){
             $http({
                 url: contextPath + 'api/V1/orders/clear/' + order.id,
                 method: 'GET',

             }).then(function (response){
                 $scope.loadOrders(currentPageIndex);
             });
     }

    $scope.checkOut = function(){
                alert('Денег НЕТ! Но вы держитесь.');
                }

    $scope.clear = function (){
             $http({
                 url: contextPath + 'api/V1/orders',
                 method: 'DELETE',
             }).then(function (response){
                 $scope.loadOrders();
             });
         }

//    $scope.isUserAdmin = function(){
//         if(!$localStorage.MarketUser){
//                    return false;
//                }
//            if($localStorage.MarketUser.username == "admin"){   //TODO:Переделать на проверку прав позже
//                return true;
//            } else {
//                return false;
//            }
//        }
     $scope.loadOrders();
});