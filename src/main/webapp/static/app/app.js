var app = angular.module('KYCApp', ['ngRoute', 'LocalStorageModule'])
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when("/login", {
                    controller: "loginController",
                    templateUrl: "login.html"
                })
                .when("/home",
                {
                    controller: "homeController",
                    templateUrl: "Innerpage.html"
                }).
                otherwise({ redirectTo: "/login" });
        }])
.constant('webAPI', {
    apiServiceBaseUri: "http://10.55.1.19:8088/NobleMktKYC/",
    ClientUri: "http://10.55.1.19:8088/NobleMktKYC/static/"
});
angular.element(document).ready(function () {
    angular.bootstrap(document, ['KYCApp']);
});

