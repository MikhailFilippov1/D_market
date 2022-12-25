angular.module('market-front').controller('registerController', function ($scope, $http, $localStorage, $location){
    const contextPath = 'http://localhost:5555/auth/';

    $scope.register = function(){
        $http.post (contextPath + 'register', $scope.reguser).then(function (response){
             if(response.data.token){
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.MarketUser = {username: $scope.reguser.username, token: response.data.token};
                $localStorage.reguser = null;
                $location.path('/');
                }
        });
    }
});