angular.module('market-front').controller('infoController', function ($scope, $http, $routeParams, $location){
    const contextPath = 'http://localhost:5555/core/';

    $scope.updateInfoForm = function(){
                        $http.get (contextPath + 'api/V1/products/' + $routeParams.productId)
                            .then(function successCallback(response){
                            $scope.infoProduct = response.data;
                            }, function failureCallback(response){
                                 alert(response.data.messages);
                             });
                    }

    $scope.navToStorePage = function(){
        $location.path('/store');
    }

    $scope.updateInfoForm();
});