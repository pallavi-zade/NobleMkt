'use strict';
app.factory('homeService', ['$http', 'webAPI', function ($http, webAPI) {

    var homeServiceFactory = {};
    var serviceBase = webAPI.apiServiceBaseUri;
    var clientURL = webAPI.ClientUri;
    var _addPersonalInfo = function (model) {
        return $http.post(serviceBase + 'userInfo/' , model, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            return response;
        });
    };
    var _addEntityInfo = function (model) {
        return $http.post(serviceBase + 'userInfo/' , model, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            return response;
        });
    };
    var _addAccountInfo = function (model) {
        return $http.post(serviceBase + 'userInfo/' , model, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            return response;
        });
    };
    var _uploadFile = function (file) {
        return $http.post(serviceBase + 'uploadFile/', file, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).then(function (response) {
            return response;
        });
    };
    var _getCountries = function () {
        return $http.get(clientURL + 'data/countryList.txt').then(function (response) {
            return response;
        });
    };
    var _getCurrency = function () {
        return $http.get(clientURL + 'data/currencyList.txt').then(function (response) {
            return response;
        });
    };

    homeServiceFactory = {
        addPersonalInfo: _addPersonalInfo,
        addEntityInfo: _addEntityInfo,
        addAccountInfo: _addAccountInfo,
        uploadFile: _uploadFile,
        getCountries: _getCountries,
        getCurrency: _getCurrency

    };
    return homeServiceFactory;
}]);
