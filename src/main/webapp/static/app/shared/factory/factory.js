app.factory('sharedFactory', function () {
    var sharedFactory = {};
    return {
        getUserName: function () {
            return sharedFactory.userName;
        },
        setUserName: function (userName) {
            sharedFactory.userName = userName;
        }
    }
});