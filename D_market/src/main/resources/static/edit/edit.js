angular.module('market-front').controller('editController', function ($scope, $http, $routeParams, $location){
    const contextPath = 'http://localhost:8189/market/';

    $scope.updateProductForm = function(){
                        $http.get (contextPath + 'api/V1/products/' + $routeParams.productId)
                            .then(function successCallback(response){
                            $scope.updatableProduct = response.data;
                            }, function failureCallback(response){
                                 alert(response.data.messages);
                             });
                    }

    $scope.updateProduct = function(){
                $http.put (contextPath + 'api/V1/products', $scope.updatableProduct)
                    .then(function successCallback(response){
                    $scope.updatableProduct = null;
                    alert('Товар успешно обновлен');
                    $location.path('/store');
                    }, function failureCallback(response){
                         alert(response.data.messages);
                     });
            }

    $scope.updateProductForm();
});