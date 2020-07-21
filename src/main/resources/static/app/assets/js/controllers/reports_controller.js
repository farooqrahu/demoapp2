'use strict';

angular.module('myApp').controller('ReportsController', ['$state', '$window', '$timeout', '$scope', '$rootScope', 'ProductService', 'AuthService', 'uiGridConstants', 'UserService', 'ReportService', function ($state, $window, $timeout, $scope, $rootScope, ProductService, AuthService, uiGridConstants, UserService, ReportService) {

    let reportsController = null;
    $scope.allGlData = null;


    $scope.findGLData = function () {
        ReportService.findGLData().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allGlData = result.data;
                console.log($scope.allGlData);
            }
        });
    };


    $scope.getPurchaseData = function () {
        ReportService.getPurchaseData().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.purchaseData.data = result.data;

            }
        });
    };
    $scope.getCustomerSaleData = function () {
        ReportService.getCustomerSaleData().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.customerSaleData.data = result.data;
            }
        });
    };
    $scope.getBranchSaleData = function () {
        ReportService.getBranchSaleData().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.branchSaleData.data = result.data;
                console.log($scope.allGlData);
            }
        });
    };

    $scope.purchaseAllColumns = [{name: 'product'},
        {name: 'purchaseDate'},
        {name: 'unitPrice'},
        {name: 'quantity'},
        {name: 'totalAmount'},
        {name: 'branch'},
    ];

    $scope.purchaseData = {
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
        columnDefs: $scope.purchaseAllColumns
    };


    $scope.purchaseData.onRegisterApi = function (gridApi) {
        $scope.purchaseData = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;

    };

    $scope.branchSaleColumns = [{name: 'product'},
        {name: 'saleDate'},
        {name: 'unitPrice'},
        {name: 'quantity'},
        {name: 'totalAmount'},
        {name: 'branch'},
    ];

    $scope.branchSaleData = {
        rowHeight: 40,
        enableFiltering: true,
        enableGridMenu: true,
        enableRowSelection: true,
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
        columnDefs: $scope.branchSaleColumns
    };

    $scope.branchSaleDataSel = [];
    $scope.branchSaleData.onRegisterApi = function (gridApi) {
        $scope.branchSaleData = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;
        gridApi.selection.on.rowSelectionChanged($scope, function (row) {

            var msg = 'row selected ' + row.isSelected;
            if(row.isSelected){
                $scope.branchSaleDataSel.push(row.entity);
            }else{
                var index = $scope.branchSaleDataSel.indexOf(row.entity);
                $scope.branchSaleDataSel.splice(index,1);

            }

        });

        gridApi.selection.on.rowSelectionChangedBatch($scope, function (rows) {
            var msg = 'rows changed ' + rows.length;

        });

    };
//

    $scope.customerSaleColumns = [{name: 'productName'},
        {name: 'saleDate'},
        {name: 'salePrice'},
        {name: 'quantity'},
        {name: 'totalSaleAmount'},

    ];

    $scope.customerSaleData = {
        rowHeight: 40,
        enableFiltering: true,
        enableGridMenu: true,
        enableRowSelection: true,
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
        columnDefs: $scope.customerSaleColumns
    };


    $scope.customerSaleDataSel = [];
    $scope.customerSaleData.onRegisterApi = function (gridApi) {
        $scope.customerSaleData = gridApi;

        //set gridApi on scope
        $scope.gridApi = gridApi;
        gridApi.selection.on.rowSelectionChanged($scope, function (row) {

            var msg = 'row selected ' + row.isSelected;
            if(row.isSelected){
                $scope.customerSaleDataSel.push(row.entity);
            }else{
                var index = $scope.customerSaleDataSel.indexOf(row.entity);
                $scope.customerSaleDataSel.splice(index,1);

            }
        });

        gridApi.selection.on.rowSelectionChangedBatch($scope, function (rows) {
            var msg = 'rows changed ' + rows.length;

        });
    };


    $scope.getPurchaseProduct = function () {
        ReportService.getPurchaseProduct().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.purchaseProduct.data = result.data;

            }
        });
    };

    $scope.purchaseProductAllColumns = [{name: 'product'},
        {name: 'purchaseDate'},
        {name: 'unitPrice'},
        {name: 'quantity'},
        {name: 'totalAmount'},
        {name: 'transactionType'},
        {
            name: 'edit',
            displayName: 'Return Product',
            cellTemplate: '<button id="editBtn" type="button" class="btn btn-sm btn-inverse-primary mdi mdi-redo  " ng-click="grid.appScope.edit(row.entity)" >'
        },
    ];

    $scope.purchaseProduct = {
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
        columnDefs: $scope.purchaseProductAllColumns
    };


    $scope.purchaseProduct.onRegisterApi = function (gridApi) {
        $scope.purchaseData = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;

    };

    $scope.edit = function (entity) {
        console.log(entity);
        $scope.product = entity;
        $scope.newQuantity = 0;
        $scope.editUserBol = true;
        $('#productModal').modal('show');
    };


    $scope.calculateTotalRetAmount = function () {
        if ($scope.newQuantity) {
            let availableQuantity = parseInt($scope.product.quantity);
            let newQuantity = parseInt($scope.newQuantity);

            if (newQuantity > availableQuantity) {
                $scope.newQuantity = 0;
                $scope.quantityExceeded = "Quantity can not be returned";
            } else {
                $scope.quantityExceeded = null;
                $scope.product.totalAmount = $scope.product.unitPrice * $scope.newQuantity;
            }
        }
    };


    $scope.addProductReturn = function () {
        $scope.product.newQuantity = parseInt($scope.newQuantity);
        console.log($scope.product);
        ReportService.addProductReturn($scope.product).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $scope.confirmPassword = null;
                $('#productModal').modal('hide');
                var index = $scope.purchaseProduct.data.indexOf($scope.product);
                $scope.purchaseProduct.data.splice(index, 1);

                $rootScope.runSweetAlertMsg('Return Product', 'Product return successful !', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Return Product', result.data.message, 'error');
            }
        });
    };


    $scope.getReturnedProducts = function () {
        ReportService.getReturnedProducts().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.productReturn.data = result.data;

            }
        });
    };

    $scope.returnProductAllColumns = [{name: 'product'},
        {name: 'salepurchaseDate'},
        {name: 'unitPrice'},
        {name: 'quantity'},
        {name: 'totalAmount'},
        {name: 'transactionType'},
    ];

    $scope.productReturn = {
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
        columnDefs: $scope.returnProductAllColumns
    };

    $scope.productReturn.onRegisterApi = function (gridApi) {
        $scope.productReturn = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;
    };
    $scope.generateCustomerInvoice = function () {
        ReportService.generateInvoice('Customer').then(function (result) {
                if (result.status == "200") { // check if we get the data back
                    $scope.invoiceNumber=result.data;
                    if ($scope.customerSaleDataSel <= 0) {
                        $rootScope.runSweetAlertMsg('Customer Invoice', 'Please select at least 1 (one) item', 'error');
                    } else {
                        let subtotal = 0;
                        angular.forEach($scope.customerSaleDataSel, function (value, key) {
                            subtotal += value.totalSaleAmount;
                        });
                        $state.go("customerSaleInvoice", {obj: $scope.customerSaleDataSel, subTotal:subtotal,invoiceNumber:$scope.invoiceNumber});
                    }
                }
            });

    };

    $scope.generateBranchInvoice = function () {
        ReportService.generateInvoice('Branch').then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.invoiceNumber=result.data;

        if ($scope.branchSaleDataSel <= 0) {
            $rootScope.runSweetAlertMsg('Branch Invoice', 'Please select at least 1 (one) item', 'error');
        } else {
            let subtotal = 0;
            angular.forEach($scope.branchSaleDataSel, function (value, key) {
                subtotal += value.totalAmount;
            });
            $state.go("branchSaleInvoice", {obj: $scope.branchSaleDataSel, subTotal: subtotal,invoiceNumber:$scope.invoiceNumber});
        }
            }
        });
    }

}]);
