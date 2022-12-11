(function(){
    angular.module('market-front',['ngRoute', 'ngStorage']).config(config).run(run);

    function config($routeProvider){
        $routeProvider
            .when('/', {
                templateUrl: 'start/start.html',
                controller: 'startController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/edit/:productId', {
                templateUrl: 'edit/edit.html',
                controller: 'editController'
            })
            .when('/info/:productId', {
                templateUrl: 'info/info.html',
                controller: 'infoController'
            })
            .when('/create', {
                templateUrl: 'create/create.html',
                controller: 'createController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage){
        if($localStorage.MarketUser){
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.MarketUser.token;
        }
    }
})();

angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage){
    const contextPath = 'http://localhost:8189/market/';

    $scope.tryToAuth = function(){
         $http.post (contextPath + 'auth', $scope.user)
              .then(function successCallback(response){
                    if(response.data.token){
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.MarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                    }
              }, function failureCallback(response){
                         alert(response.data.messages);
                     });
        }

     $scope.tryToLogout = function(){
            $scope.clearUser();
            if($scope.user.username){
                $scope.user.username = null;
            }
            if($scope.user.password){
                $scope.user.password = null;
            }
        };

    $rootScope.isUserLoggedIn = function(){
        if($localStorage.MarketUser){
            return true;
        } else {
            return false;
        }
    }

    $scope.clearUser = function(){
        delete $localStorage.MarketUser;
        $http.defaults.headers.common.Authorization = '';
    };
});




