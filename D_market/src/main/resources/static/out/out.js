angular.module('market-front').controller('logoutController', function ($scope, $location, $localStorage){

    $scope.tryToLogout = function(){
        $scope.clearUser();
        alert('TryToLogout -> Пользователь успешно удален');
        if($scope.user.username){
            $scope.user.username = null;
        }
        if($scope.user.password){
            $scope.user.password = null;
        }
//        $location.path('/');
    };

    $scope.clearUser = function(){
        alert('ClearUser -> Пользователь успешно удален');
        delete @localStorage.MarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.navToStorePage = function(){
    alert('Не удаляюсь, ухожу на страницу продуктов');
        $location.path('/store');
    };
});