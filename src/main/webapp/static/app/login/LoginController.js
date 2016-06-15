app.controller('loginController', [ '$scope', '$location',

'localStorageService', function($scope, $location,

localStorageService) {

	$('body').addClass('mainlogin');
	$scope.login = {};
	$scope.login = function() {
		$scope.loginForm.$setSubmitted(true);
		if ($scope.loginForm.$valid) {
			localStorageService.add('userName', $scope.login.userName);
			$location.path('/home');
		}
	}
} ]);