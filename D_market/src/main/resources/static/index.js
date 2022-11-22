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
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/out', {
                templateUrl: 'out/out.html',
                controller: 'logoutController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http){
    }
})();

angular.module('market-front').controller('indexController', function ($scope, $rootScope,  $http){
    const contextPath = 'http://localhost:8189/market/';
});




