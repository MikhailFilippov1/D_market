angular.module('market-front').controller('authController', function ($scope, $rootScope, $http, $localStorage, $location){
    const contextPath = 'http://localhost:8189/market/';

    $scope.tryToAuth = function(){
                 $http.post (contextPath + 'auth', $scope.user)
                      .then(function successCallback(response){
                            if(response.data.token){
                            $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                            $localStorage.MarketUser = {username: $scope.user.username, token: response.data.token};
                            $scope.user.username = null;
                            $scope.user.password = null;
                            $location.path('/store');
                            }
                      }, function failureCallback(response){
                                 alert(response.data.messages);
 //                                delete @localStorage.MarketUser;
 //                                $scope.user.username = null;
 //                                $scope.user.password = null;
                                 $location.path('/');
                             });
    }

    $rootScope.isUserLoggedIn = function(){
                if($localStorage.MarketUser){
                    return true;
                } else {
                    return false;
                }
    }
});