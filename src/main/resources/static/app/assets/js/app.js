angular.module('myApp', ['ui.router', 'ngTouch', 'ui.grid', 'ui.grid.pagination','ui.grid.edit','ui.grid.rowEdit', 'ui.grid.autoResize', 'ui.grid.selection','ui.grid.resizeColumns', 'ui.grid.moveColumns','ui.toggle'])

    // .config(['$httpProvider', function ($httpProvider) {
    //     $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    // }])

    .run(function ($http, AuthService, $rootScope) {
        // $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
        //     $http.get('api/user').success(function (data) {
        //         if (data.name == undefined) {
        //             if (toState.name != 'login') {
        //                 $rootScope.loginUserData=null;
        //                 event.preventDefault();
        //                 $state.go('login');
        //             }
        //         } else {
        //             $rootScope.loginUserData = data;
        //             $rootScope.totalPurchase=data.totalPurchase;
        //             $rootScope.totalBranchSale=data.totalBranchSale;
        //             $rootScope.totalCustomerSale=data.totalCustomerSale;
        //             $rootScope.totalIncome=data.totalIncome;
        //         }
        //
        //     }).error(function () {
        //         $state.go('login');
        //     });
        //
        // });

        $rootScope.sweetAlertTest = function (msg) {
            Swal.fire(
                'Good job!',
                msg,
                'success'
            )
        };
        $rootScope.runSweetAlertMsg = function (label, msg, type) {
            Swal.fire(label, msg, type)
            //  success
            //    error
            //  warning
            //     info
            //  question
        };


        $rootScope.regex = {
            alphaNumeric: '^[ A-z0-9_]*$',
            alphabets: '^[ A-Za-z_]*$',
            passport: '^[A-z]{2}[0-9]{7}',
            cnic: '^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$',
            numeric: '^[0-9]*$',
            ntn: '^[0-9+-]*$',
            fax: '^[0-9+-]*$',
            phone: '^[0-9\+\-]*$',
            address: '^[A-Z a-z0-9#/"\'+,:\)\(._-]*$',
            text: '^[a-z A-Z0-9/""\\\]\[:;|=,+*?_<>]+$',
            decimal: '^[.0-9]*$',
            airlineNumber: '^[0-9-]*$',
            ticketNumber: '^[0-9]{3}(-)?[0-9]{4}(-)?[0-9]{3}(-)?[0-9]{3}(-)?$',
            code: '^[ A-z0-9._]*$',
        };

        $rootScope.errMsg = {
            required: "Field is required",
            number: "Numbers only",
            nonNegativeNumbers: "Non negative numbers only",
            phone: "Incorrect number format",
            alphabets: "Alphabets only",
            special: "Special characters are not allowed",
            max: "Max Value Reached",
            email: "Invalid email",
            decimal: 'Decimal numbers only',
            address: 'Some special characters are not allowed',
            airlineNumber: "Only numbers and hyphen allow",
            ticketNumber: "Enter correct format (i.e. 999-9999-999-999)",
            alphaNumeric: "Special Character not allowed"
        };

        /!*ng-patten regex values are stored here to use them for validation.*!/
        $rootScope.alphaNumeric = /^[a-z0-9 ]+$/i;
        $rootScope.numeric = /^[0-9]*$/;
        $rootScope.mobileNo = /^[0-9+]{10,13}$/;
        $rootScope.cnic = /^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$/;
        $rootScope.url = /https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9]\.[^\s]{2,}/;
        $rootScope.smtp = /^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\-]*[a-zA-Z0-9])\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\-]*[A-Za-z0-9])$/;
        $rootScope.email = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;

        $rootScope.printDiv = function (div) {
            var docHead = document.head.outerHTML;
            var printContents = document.getElementById(div).outerHTML;
            var winAttr = "location=yes, statusbar=no, menubar=no, titlebar=no, toolbar=no,dependent=no, width=865, height=600, resizable=yes, screenX=200, screenY=200, personalbar=no, scrollbars=yes";

            var newWin = window.open("", "_blank", winAttr);
            var writeDoc = newWin.document;
            writeDoc.open();
            writeDoc.write('<!doctype html><html>' + docHead + '<body onLoad="window.print()">' + printContents + '</body></html>');
            writeDoc.close();
            newWin.focus();
        };

    });


