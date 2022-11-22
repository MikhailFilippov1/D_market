angular.module('market-front').controller('cartController', function ($scope, $http, $location){
    const contextPath = 'http://localhost:8189/market/';
    let currentPageIndex = 1;

    $scope.loadCart = function () {
        $http ({
            url: contextPath + 'api/V1/products/cart',
            method: 'GET',
        }).then(function successCallback(response){
            $scope.productList = response.data;
            }, function failureCallback(response){
            alert(response.data.messages);
            });
    }

    $scope.navToInfoPage = function(productId){
              console.log('Переход в окно информации');
                 $location.path('/info/' + productId);
              }

    $scope.clearCart = function (){
             $http({
                 url: contextPath + 'api/V1/products/cart/clear',
                 method: 'GET',
             }).then(function (response){
                 $scope.loadCart();
             });
     }

     $scope.deleteProduct = function (productId){
     console.log(productId);
             $http({
                 url: contextPath + 'api/V1/products/cart/clear/' + productId,
                 method: 'GET',

             }).then(function (response){
                 $scope.loadCart();
             });
     }

     $scope.sumF=function(a){
         $scope.sumEnd=0;
         for(let i=0;i<a.length;i++){
           $scope.sumEnd +=parseInt(a[i]);
         }
       }

     $scope.loadCart();
});