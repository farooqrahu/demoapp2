'use strict';

angular.module('myApp').controller('ProductComController', ['$window', '$timeout', '$scope', '$rootScope', 'ProductComService', 'AuthService', 'uiGridConstants', function ($window, $timeout, $scope, $rootScope, ProductComService, AuthService, uiGridConstants) {

    var productComControllerVm = null;
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


    $scope.findAllProductCompanies = function () {
        ProductComService.findAllProductCompanies().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allProductCompanyDataUIGrid.data = result.data;
            }
        })
    };

    $scope.setStatus = function (status) {
        $scope.product.isActive = status;
    };
    $scope.addProductCom = function () {
        if ($scope.editUserBol) {
            $scope.editProduct($scope.product);
        } else {
            ProductComService.addProductCat($scope.product).then(function (result) {
                if (result.status == "201") { // check if we get the data back
                    $scope.confirmPassword = null;
                    $scope.addProductForm.$setPristine();
                    $('#productModal').modal('hide');
                    $scope.allProductCompanyDataUIGrid.data.push(result.data);
                    $rootScope.runSweetAlertMsg('Add New Product Category', 'Add new successful !', 'success');
                } else {
                    $rootScope.runSweetAlertMsg('Add New Product Category', result.data.message, 'error');
                }
            });
        }
    };

    $scope.clearData = function () {
        $scope.product = null;
        $scope.editUserBol = false;


    };

    $scope.deleteSelected = function () {
        console.log($scope.allProductCompanyDataUIGrid);
        $rootScope.runSweetAlertMsg('Delete All', 'Coming Soon!', 'info');
    };


    $scope.listAllColumns = [{name: 'name'},
        {name: 'companyDesc'},
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

    $scope.allProductCompanyDataUIGrid = {
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


    $scope.allProductCompanyDataUIGrid.onRegisterApi = function (gridApi) {
        $scope.allProductCompanyDataUIGrid = gridApi;
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
        ProductComService.editProduct(entity).then(function (result) {
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
        ProductComService.deleteProduct(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back

                var index = $scope.allProductCompanyDataUIGrid.data.indexOf(entity);
                $scope.allProductCompanyDataUIGrid.data.splice(index, 1);
                $rootScope.runSweetAlertMsg('Delete Product', 'Product Deleted Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Delete Product', 'Delete Product failed!', 'error');
            }
        });

    };


}]);
