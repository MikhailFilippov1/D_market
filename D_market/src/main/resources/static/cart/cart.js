angular.module('market-front').controller('cartController', function ($scope, $http, $location){
    const contextPath = 'http://localhost:8189/market/';
    let currentPageIndex = 1;

    $scope.loadCart = function () {
        $http ({
            url: contextPath + 'api/V1/cart',
            method: 'GET',
        }).then(function successCallback(response){
            $scope.cart = response.data;
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
                 url: contextPath + 'api/V1/cart/clear',
                 method: 'GET',
             }).then(function (response){
                 $scope.loadCart();
             });
     }

     $scope.deleteProduct = function (productId){
             $http({
                 url: contextPath + 'api/V1/cart/clear/' + productId,
                 method: 'GET',

             }).then(function (response){
                 $scope.loadCart();
             });
     }

     $scope.checkOut = function(){
            alert('Денег НЕТ! Но вы держитесь.');
            }

     $scope.sumF=function(a){
         $scope.sumEnd=0;
         for(let i=0;i<a.length;i++){
           $scope.sumEnd +=parseInt(a[i]);
         }
       }

       $scope.navToStorePage = function(){
               $location.path('/store');
           }

   $scope.changeQuantity = function (productId, delta){
           $http ({
               url: contextPath + 'api/V1/cart/changeQuantity',
               method: 'GET',
               params: {
                   productId: productId,
                   delta: delta
               }
           }).then(function (response) {
               $scope.loadCart();
           });
       };

       $scope.order = function(){
           $http.post(contextPath + 'api/V1/orders');
           $location.path('/store');
       }

     $scope.loadCart();
});