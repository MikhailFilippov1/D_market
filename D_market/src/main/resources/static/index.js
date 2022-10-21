angular.module('D_market-front', []).controller('indexController', function ($scope, $http){
    const contextPath = 'http://localhost:8189/market';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http ({
            url: contextPath + '/api/V1/products',
            method: 'GET',
            params: {
                p: pageIndex
                }
        }).then(function(response){
            $scope.productPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productPage.totalPages);
            });
    };

    $scope.changePrice = function (productId, delta){
        $http ({
            url: contextPath + '/product/changePrice',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    };

    $scope.createNewProduct = function(){
        $http.post (contextPath + '/api/V1/products', $scope.newProduct)
            .then(function successCallback(response){
                $scope.loadProducts(currentPageIndex);
                $scope.newProduct = null;
            }, function failureCallback(response){
                 alert(response.data.message);
             });
    }

    $scope.deleteProduct = function (product){
        $http({
            url: contextPath + '/api/V1/products/' + product.id,
            method: 'DELETE',

        }).then(function (response){
            $scope.loadProducts(currentPageIndex);
        });
    };

    $scope.loadProduct = function (product){
            alert(product.title);
        };

    $scope.generatePagesIndexes = function(startPage, endPage){
            let arr = [];
                for(let i = startPage; i < endPage + 1; i++){
                    arr.push(i);
                }
                return arr;
    }

    $scope.updateProduct = function(){
                $http.put (contextPath + '/api/V1/products', $scope.updatableProduct)
                    .then(function successCallback(response){
                    $scope.loadProducts(currentPageIndex);
                    $scope.updatableProduct = null;
                    }, function failureCallback(response){
                         alert(response.data.message);
                     });
            };

    $scope.updateProductForm = function(productId){
                    $http.get (contextPath + '/api/V1/products/' + productId)
                        .then(function successCallback(response){
                        $scope.updatableProduct = response.data;
                        }, function failureCallback(response){
                             alert(response.data.message);
                         });
                };

    $scope.loadProducts();
});



