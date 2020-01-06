angular.module('myApp')

.controller('ProductController', function ($scope, $http, $rootScope, $timeout, AuthService) {

    $scope.user = AuthService.user;

    $http.get('http://localhost:8080/products').then(function (response) {
        $rootScope.products = response.data;
    });


    $rootScope.loadAuthors = function () {
        $http.get('http://localhost:8080/authors').then(function (response) {
            $rootScope.authors = response.data;
        });
    };

    $rootScope.loadAuthors();

    $scope.deleteProduct = function (product) {
        var idx = $scope.products.indexOf(product);
        $http.delete('http://localhost:8080/delete/' + product.id).success(function () {
            $scope.products.splice(idx, 1);
            $scope.message = "Product deleted successfully!";
            $timeout(function(){$scope.message = '';}, 3000);
        }).error(function () {
            $scope.message = "Oops, can't delete this product!";
            $timeout(function(){$scope.message = '';}, 3000);
        });
    };

    $scope.productsDetails = function (product) {

        $rootScope.productsId = product.id;
        $rootScope.productsName = product.name;
        $rootScope.productsAuthor = product.author.name;
        $rootScope.productsDuration = product.duration;
        $rootScope.productsDate = product.date;
        $rootScope.productsAlbum = product.album;

    };


    $scope.editProduct = function (product) {

        $rootScope.idScope = product.id;
        $rootScope.nameScope = product.name;
        $rootScope.authorScope = product.author;
        $rootScope.durationScope = product.duration;
        $rootScope.dateScope = product.date;
        $rootScope.albumScope = product.album;
        $rootScope.contentScope = product.content;
    };


    $scope.addProductProductlist = function (product) {

        $http.post('http://localhost:8080/productList/add/' + product.id)
            .success(function () {
            $scope.message = "Product added successfully!";
            $timeout(function(){$scope.message = '';}, 3000);
        })
            .error(function () {
            $scope.message = "This product already in a productList!";
            $timeout(function(){$scope.message = '';}, 3000);
        });

    };

    $scope.downloadProduct = function (product) {

        $http.post('http://localhost:8080/download/product/' + product.id).then(function () {
            document.getElementById("download").submit();
        });

    };

});
