'use strict';

angular.module('myApp').controller('ProductSaleCusController', ['$window', '$timeout', '$scope', '$rootScope', 'AuthService', 'uiGridConstants', 'UserService', 'ProductSaleCusService', function ($window, $timeout, $scope, $rootScope, AuthService, uiGridConstants, UserService, ProductSaleCusService) {

    var productControllerVm = null;
    var edit = false;
    $scope.allProducts = null;
    $scope.quantity = 0;
    $scope.productSale = {id: '0', quantity: '0', branch: '', product: '',showProductList:[]};
    $scope.productSale.newQuantity = 0;
    $scope.productCategoryDto = {productCategory: ""};
    $scope.productCompanyDto = {name: ""};
    $scope.product = {productCategoryDto: [], productCompanyDto: []};
    $scope.branchId = null;
    $scope.totalAmount = 0;
    $scope.quantityExceeded = null;

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
        ProductSaleCusService.findAllProducts().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allProductsDataUIGrid.data = result.data;
                UserService.findAllCustomerBranches().then(function (result) {
                    if (result.status == "200") { // check if we get the data back
                        $scope.branches = result.data;
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
                ProductSaleCusService.addProduct($scope.product).then(function (result) {
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

    $scope.addProductSale = function () {
        $scope.productSale.newQuantity = parseInt($scope.productSale.newQuantity);
        $scope.productSale.product = $scope.product.id;
        console.log($scope.productSale);
        ProductSaleCusService.addProductStock($scope.productSale).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $scope.confirmPassword = null;
                $('#productModal').modal('hide');
                $rootScope.runSweetAlertMsg('Add Product Sale ', 'Add product stock successful !', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Add New Product', result.data.message, 'error');
            }
        });
    };

    $scope.clearData = function () {
        $scope.product = null;
        $scope.editUserBol = false;


    };

    $scope.saleProductSaleToCustomer = function () {
        $scope.productSale.product = $scope.product.id;

        ProductSaleCusService.saleProductSaleToCustomer($scope.productSale).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $scope.confirmPassword = null;
                $('#productModal').modal('hide');
                $rootScope.runSweetAlertMsg('Add Product Sale ', 'Add product stock successful !', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Add New Product', result.data.message, 'error');
            }
        });
    };


    $scope.deleteSelected = function () {
        console.log($scope.allProductsDataUIGrid);
        $rootScope.runSweetAlertMsg('Delete All', 'Coming Soon!', 'info');
    };


    $scope.listAllColumns = [{name: 'name'},
        {name: 'model'},
        {name: 'productCategory.productCategory', displayName: 'Category'},
        {name: 'productCompany.name', displayName: 'Company'},
        {name: 'newQuantity', enableCellEdit: true, displayName: 'Add Quantity'},
        /*
                {
                    name: 'edit',
                    displayName: 'Edit Sale',
                    cellTemplate: '<button id="editBtn" type="button" class="btn btn-sm btn-primary mdi mdi-pen-plus green " ng-click="grid.appScope.edit(row.entity)" >'
                }
        */
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
        //set gridApi on scope

        gridApi.selection.on.rowSelectionChanged($scope,function(row){
            if ($scope.productSale.showProductList.length > 0) {
                if(row.entity.newQuantity==undefined){
                    $rootScope.runSweetAlertMsg('Sale Product', 'Add Product Quantity!', 'error');
                    return;
                }
                var index = $scope.productSale.showProductList.indexOf(row.entity);
                if (index === -1) { //this is where i have bug. match the condition with -1

                    $scope.productSale.showProductList.push(row.entity);
                } else {
                    $scope.productSale.showProductList.splice(index, 1);
                }
            } else {
                if(row.entity.newQuantity==undefined){
                    $rootScope.runSweetAlertMsg('Sale Product', 'Add Product Quantity!', 'error');
                    return;
                }
                $scope.productSale.showProductList.push(row.entity);
            }

        });
    };

    $scope.edit = function (entity) {
        console.log(entity);
        $scope.product = entity;
        $scope.productCategory.value = $scope.product.productCategory.productCategory;
        $scope.productCompany.value = $scope.product.productCompany.name;
        $scope.showSaleBranchWise();
        /*if ($scope.product.isActive) {
            $("#active").prop("checked", true);
        } else {
            $("#inactive").prop("checked", true);
        }*/
        $scope.productSale.newQuantity = 0;
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
        ProductSaleCusService.editProduct(entity).then(function (result) {
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
        ProductSaleCusService.deleteProduct(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back

                var index = $scope.allProductsDataUIGrid.data.indexOf(entity);
                $scope.allProductsDataUIGrid.data.splice(index, 1);
                $rootScope.runSweetAlertMsg('Delete Product', 'Product Deleted Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Delete Product', 'Delete Product failed!', 'error');
            }
        });

    };
    $scope.showSaleBranchWise = function (branch) {
        var data = {'branch': 1, 'product': $scope.product.id};
        ProductSaleCusService.getStockBranchWise(data).then(function (result) {
            if (result.status == "201") {
                $scope.productSale = result.data;
                $scope.productSale.branch = null;
            }
        });
    };
    /*   $scope.showSaleBranchWise = function (branch) {
           if (branch == null || $scope.product.id == null) {
               $scope.productSale = {quantity: 0};
               return null;
           }
           var data = {'branch': branch, 'product': $scope.product.id};
           ProductSaleCusService.getSaleBranchWise(data).then(function (result) {
               if (result.status == "201") {
                   $scope.productSale = result.data;
               }
           });
       };
    */
    $scope.calculateTotalSalePurAmount = function () {
        if ($scope.productSale.newQuantity) {
            $scope.productSale.newTotalSaleAmount = $scope.productSale.salePrice * $scope.productSale.newQuantity;
            $scope.productSale.newTotalPurchaseAmount = $scope.productSale.purchasePrice * $scope.productSale.newQuantity;
        }
    };

    $scope.calculateTotalSaleAmount = function () {
        if ($scope.productSale.newQuantity) {
            let availableQuantity = parseInt($scope.productSale.quantity);
            let newQuantity = parseInt($scope.productSale.newQuantity);
            if (newQuantity > availableQuantity) {
                $scope.productSale.newQuantity = 0;
                $scope.quantityExceeded = "Quantity entered not available in stock";
            } else {
                $scope.quantityExceeded = null;

            }
            $scope.productSale.newTotalSaleAmount = parseInt($scope.productSale.salePrice) * newQuantity;
        }
    };

    $scope.showProductModal = function () {
        $('#showProductModal').modal('show');
    }
}]);
