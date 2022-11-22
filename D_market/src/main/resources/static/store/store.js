angular.module('market-front').controller('storeController', function ($scope, $http, $location){
    const contextPath = 'http://localhost:8189/market/';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http ({
            url: contextPath + 'api/V1/products',
            method: 'GET',
            params: {
                p: pageIndex
                }
        }).then(function(response){
            $scope.productPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productPage.totalPages);
            });
    }

     $scope.generatePagesIndexes = function(startPage, endPage){
                let arr = [];
                    for(let i = startPage; i < endPage + 1; i++){
                        arr.push(i);
                    }
                    return arr;
        }

     $scope.navToEditPage = function(productId){
     console.log('Переход в редактирование');
        $location.path('/edit/' + productId);
     }

     $scope.navToCreatePage = function(){
          console.log('Переход в создание');
             $location.path('/create');
     }

     $scope.navToInfoPage = function(productId){
          console.log('Переход в окно информации');
             $location.path('/info/' + productId);
          }

     $scope.deleteProduct = function (product){
             $http({
                 url: contextPath + 'api/V1/products/' + product.id,
                 method: 'DELETE',

             }).then(function (response){
                 $scope.loadProducts(currentPageIndex);
             });
     }

     $scope.addToCart = function (product){
                  $http({
                      url: contextPath + 'api/V1/products/cart/' + product.id,
                      method: 'GET',

                  }).then(function (response){
                      $scope.loadProducts(currentPageIndex);
                  });
          }

     $scope.loadProducts();
});