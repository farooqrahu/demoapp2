angular.module('myApp').config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/page-not-found');
	$stateProvider.state('nav', {
		abstract : true,
		url : '',
		views : {
			'nav@' : {
				templateUrl : 'app/views/nav.html',
				controller : 'NavController'
			}
		}
	}).state('home', {
		parent : 'nav',
		url : '/home',
		views : {
			'content@' : {
				templateUrl : 'app/views/home.html',
				controller : 'HomeController'
			}
		}
	}).state('category', {
		parent : 'nav',
		url : '/advance-search',
		views : {
			'content@' : {
				templateUrl : 'app/views/category.html',
				controller : 'HomeController'
			}
		}
	}).state('postad', {
		parent : 'nav',
		url : '/postad',
		views : {
			'content@' : {
				templateUrl : 'app/views/postad.html',
				controller : 'HomeController'
			}
		}
	}).state('contactus', {
		parent : 'nav',
		url : '/contactus',
		views : {
			'content@' : {
				templateUrl : 'app/views/contactus.html',
				controller : 'HomeController'
			}
		}
	}).state('product', {
		parent : 'nav',
		url : '/product',
		views : {
			'content@' : {
				templateUrl : 'app/views/product.html',
				controller : 'HomeController'
			}
		}
	}).state('loginRegister', {
		parent : 'nav',
		url : '/loginRegister',
		views : {
			'content@' : {
				templateUrl : 'app/views/loginRegister.html',
				controller : 'HomeController'
			}
		}
	}).state('footer', {
		abstract : true,
		url : '',
		views : {
			'footer@' : {
				templateUrl : 'app/views/footer.html',
				controller : 'NavController'
			}
		}
	}).state('page-not-found', {
		url : '/page-not-found',
		views : {
			'content@' : {
				templateUrl : 'app/views/404not.html',
				controller : 'PageNotFoundController'
			}
		}
	});
});
