'use strict';

angular.module('myApp').controller('ProductController', ['$window', '$timeout', '$scope', '$rootScope', 'ProductService', 'AuthService', 'uiGridConstants', function ($window, $timeout, $scope, $rootScope, ProductService, AuthService, uiGridConstants) {

    var productControllerVm = null;
    var edit = false;
    $scope.allProducts = null;

    $scope.productCategoryDto = {productCategory: ""};
    $scope.productCompanyDto = {name: ""};
    $scope.product = {productCategoryDto: [], productCompanyDto: []};

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
            if ($scope.productCategoryDto.productCategory != "") {
                $scope.product.productCategory = $scope.productCategoryDto;
            }
            if ($scope.productCompanyDto.name != "") {
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

    $scope.clearData = function () {
        $scope.product = null;
        $scope.editUserBol = false;


    };

    $scope.deleteSelected = function () {
        console.log($scope.allProductsDataUIGrid);
        $rootScope.runSweetAlertMsg('Delete All', 'Coming Soon!', 'info');
    };


    $scope.listAllColumns = [{name: 'name'},
        {name: 'model'},
        {name: 'productCategory.productCategory', displayName: 'Category'},
        {name: 'productCompany.name', displayName: 'Company'},

        {
            name: 'edit',
            displayName: 'Edit',
            cellTemplate: '<button id="editBtn" type="button" class="btn btn-sm btn-primary mdi mdi-pen-plus green " ng-click="grid.appScope.edit(row.entity)" >'
        },
        {
            name: 'delete',
            displayName: 'Delete',
            cellTemplate: '<button id="deleteBtn" type="button" class="btn btn-sm btn-danger mdi mdi-pen-remove red " ng-click="grid.appScope.remove(row.entity)" ></button>'
        }];

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
        gridApi.selection.on.rowSelectionChanged($scope, function (row) {
            var msg = 'row selected ' + row.isSelected;

        });

        gridApi.selection.on.rowSelectionChangedBatch($scope, function (rows) {
            var msg = 'rows changed ' + rows.length;

        });
    };

    $scope.edit = function (entity) {
        console.log(entity);
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


}]);
