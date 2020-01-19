'use strict';

angular.module('myApp').controller('ProductStockController', ['$window', '$timeout', '$scope', '$rootScope', 'ProductService', 'AuthService', 'uiGridConstants','UserService', function ($window, $timeout, $scope, $rootScope, ProductService, AuthService, uiGridConstants,UserService) {

    var productControllerVm = null;
    var edit = false;
    $scope.allProducts = null;
    $scope.quantity=0;
    $scope.productStock={id:'0',quantity:'0',branch:'',product:''};

    $scope.productCategoryDto = {productCategory: ""};
    $scope.productCompanyDto = {name: ""};
    $scope.product = {productCategoryDto: [],productCompanyDto: []};
   $scope.branchId=null;


    $scope.branches = [];

    $scope.error = "";
    $scope.editUserBol = false;
    $scope.productCategory = {
        "type": "select",
        "name": "productCategory",
        "value": "Please Select",
        "values": ["Please Select"]
    };
    $scope.productCompany = {
        "type": "select",
        "name": "productCompany",
        "value": "Please Select",
        "values": ["Please Select"]
    };

    $scope.selectProductCompany = function (name) {
        $scope.productCompanyDto.name = name;

    };
    $scope.selectProductCategory = function (name) {
        $scope.productCategoryDto.productCategory = name;

    };


    $scope.findAllProducts = function () {
        ProductService.findAllProducts().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allProductsDataUIGrid.data = result.data;
                ProductService.findAllProductCategories().then(function (result) {
                    if (result.status == "200") { // check if we get the data back
                        angular.forEach(result.data, function (value, key) {
                            $scope.productCategory.values.push(value.productCategory);
                        });
                        ProductService.findAllProductCompanies().then(function (result) {
                            if (result.status == "200") { // check if we get the data back
                                angular.forEach(result.data, function (value, key) {
                                    $scope.productCompany.values.push(value.name);
                                });
                                UserService.findAllBranches().then(function (result) {
                                    if (result.status == "200") { // check if we get the data back
                                        $scope.branches=result.data;
                                    }
                                });
                            }
                        });
                    }
                });

            }
        });
    };

    $scope.setStatus = function (status) {
        $scope.product.isActive = status;
    };
    $scope.addProduct = function () {
        if ($scope.productCategory.value == 'Please Select' || $scope.productCompany.value == 'Please Select') {
            $scope.error = "Please Select Role";
        } else {
            if($scope.productCategoryDto.productCategory!=""){
                $scope.product.productCategory = $scope.productCategoryDto;
            }
            if($scope.productCompanyDto.name!=""){
                $scope.product.productCompany = $scope.productCompanyDto;
            }
            if ($scope.editUserBol) {
                $scope.editProduct($scope.product);
            } else {
                ProductService.addProduct($scope.product).then(function (result) {
                    if (result.status == "201") { // check if we get the data back
                        $scope.confirmPassword = null;
                        $scope.addProductForm.$setPristine();
                        $('#productModal').modal('hide');
                        $scope.allProductsDataUIGrid.data.push(result.data);
                        $rootScope.runSweetAlertMsg('Add New Product', 'Add new product successful !', 'success');
                    } else {
                        $rootScope.runSweetAlertMsg('Add New Product', result.data.message, 'error');
                    }
                });
            }

        }
    };

    $scope.addProductStock=function(){
        console.log($scope.productStock);
        ProductService.addProductStock($scope.productStock).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $scope.confirmPassword = null;
                $('#productModal').modal('hide');
                $rootScope.runSweetAlertMsg('Add Product Stock ', 'Add product stock successful !', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Add New Product', result.data.message, 'error');
            }
        });
    };

    $scope.clearData = function () {
        $scope.product = null;
        $scope.editUserBol = false;


    };

    $scope.deleteSelected = function () {
        console.log($scope.allProductsDataUIGrid);
        $rootScope.runSweetAlertMsg('Delete All', 'Coming Soon!', 'info');
    };


$scope.listAllColumns= [{name: 'name', width: "100"},
    {name: 'display', width: "150"},
    {name: 'os', width: "100"},
    {name: 'osVersion', width: "150"},
    {name: 'memory', width: "100"},
    {name: 'cameras', width: "100"},
    {name: 'battery', width: "150"},
    {name: 'colors', width: "150"},
    {
        name: 'edit', width: "150",
        displayName: 'Edit Stock',
        cellTemplate: '<button id="editBtn" type="button" class="btn btn-sm btn-primary mdi mdi-pen-plus green " ng-click="grid.appScope.edit(row.entity)" >'
    }
];

    $scope.allProductsDataUIGrid = {
        rowHeight: 40,
        enableFiltering: true,
        enableGridMenu: true,
        enableRowSelection: false,
        enableSelectAll: true,
        selectionRowHeaderWidth: 50,
        infiniteScrollDown: true,
        enableColumnResizing: true,
        paginationPageSizes: [25, 50, 75],
        paginationPageSize: 25,


        enableFullRowSelection: true,
        showGridFooter: true,
        enableHorizontalScrollbar: uiGridConstants.scrollbars.WHEN_NEEDED, // Here!
        enableVerticalScrollbar: uiGridConstants.scrollbars.WHEN_NEEDED,
        columnDefs: $scope.listAllColumns
    };


    $scope.allProductsDataUIGrid.onRegisterApi = function (gridApi) {
        $scope.allProductsDataUIGrid = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;

    };

    $scope.edit = function (entity) {
        console.log(entity);
        $scope.quantity=0;
        $scope.productStock={quantity:0};
        $scope.branchId=null;

        $scope.product = entity;
        $scope.productCategory.value = $scope.product.productCategory.productCategory;
        $scope.productCompany.value = $scope.product.productCompany.name;

        /*if ($scope.product.isActive) {
            $("#active").prop("checked", true);
        } else {
            $("#inactive").prop("checked", true);
        }*/

        $scope.editUserBol = true;
        $('#productModal').modal('show');
    };

    $scope.remove = function (entity) {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.value) {
                $scope.deleteProduct(entity);
            }
        });
    };

    $scope.editProduct = function (entity) {
        ProductService.editProduct(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $('#productModal').modal('hide');
                $scope.editUserBol = false;
                $rootScope.runSweetAlertMsg('Edit Product', 'Product Edited Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Edit Product', 'Edit Product failed!', 'error');
            }
        });
    };

    $scope.deleteProduct = function (entity) {
        ProductService.deleteProduct(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back

                var index = $scope.allProductsDataUIGrid.data.indexOf(entity);
                $scope.allProductsDataUIGrid.data.splice(index, 1);
                $rootScope.runSweetAlertMsg('Delete Product', 'Product Deleted Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Delete Product', 'Delete Product failed!', 'error');
            }
        });

    };
    $scope.showStockBranchWise= function (branch) {
        if(branch==null || $scope.product.id==null){
            $scope.productStock={quantity:0};
            return null;
        }
        var data = {'branch': branch, 'product': $scope.product.id};
        ProductService.getStockBranchWise(data).then(function (result) {
            $scope.productStock=result.data;

        });
    }

}]);
