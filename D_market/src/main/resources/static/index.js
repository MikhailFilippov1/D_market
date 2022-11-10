(function(){
    angular.module('market-front',['ngRoute']).config(config).run(run);

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

    function run($rootScope, $http){
    }
})();

angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http){
    const contextPath = 'http://localhost:8189/market/';

});




