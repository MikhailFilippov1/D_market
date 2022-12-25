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
            .when('/order', {
                templateUrl: 'order/order.html',
                controller: 'orderController'
                        })
            .when('/orderItem/:orderId', {
                templateUrl: 'orderItem/orderItem.html',
                controller: 'orderItemController'
                        })
            .when('/register', {
                templateUrl: 'register/register.html',
                controller: 'registerController'
                        })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage){
        if($localStorage.MarketUser){
            try{
                let jwt = $localStorage.MarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date.getTime() / 1000);
                if(currentTime > payload.exp){
                    console.log("Token is expired!");
                    delete $localStorage.MarketUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch(e){
            }
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.MarketUser.token;
        }

        if(!$localStorage.MarketUser){
            $http.get('http://localhost:5555/cart/api/V1/cart/generate_uuid')
                .then(function successCallback(response){
                    $localStorage.marketGuestCartId = response.data.value;
                });
        }
    }
})();

angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location){
    const contextPath = 'http://localhost:5555/auth/';

    $scope.tryToAuth = function(){
         $http.post (contextPath + 'auth', $scope.user)
              .then(function successCallback(response){
                    if(response.data.token){
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.MarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                    }
 //                   $location.path('/');
              }, function failureCallback(response){
                         alert(response.data.messages);
                     });
                     $location.path('/');
        }

     $scope.tryToLogout = function(){
            $scope.clearUser();
            if($scope.user.username){
                $scope.user.username = null;
            }
            if($scope.user.password){
                $scope.user.password = null;
            }
            $location.path('/');
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




