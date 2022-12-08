angular.module('market-front').controller('createController', function ($scope, $http, $routeParams, $location){
    const contextPath = 'http://localhost:5555/core/';

    $scope.createNewProduct = function(){
    if($scope.newProduct == null){
        alert('Вы не заполнили поля!');
        return;
    }
             $http.post (contextPath + 'api/V1/products', $scope.newProduct)
                 .then(function successCallback(response){
                     $scope.newProduct = null;
                     alert('Товар успешно создан');
                     $location.path('/store');
                 }, function failureCallback(response){
                      alert(response.data.messages);
             });
     }

});