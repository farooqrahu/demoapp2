angular.module('myApp', [ 'ui.router' ,'ngCookies'])

.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
} ])

.run(function(AuthService, $rootScope, $state,$cookieStore) {

	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
        if (!$cookieStore.get('isLogged')) {
            if (toState.name != 'login' && toState.name != 'register') {
                event.preventDefault();
                $state.go('login');
            }
        }

	});

    $rootScope.sweetAlertTest= function(msg) {
        Swal.fire(
            'Good job!',
            msg,
            'success'
        )
    };
    $rootScope.runSweetAlertMsg= function(label,msg,type) {
        Swal.fire(label,msg,type)
        //  success
        //    error
        //  warning
        //     info
        //  question
    };
});


