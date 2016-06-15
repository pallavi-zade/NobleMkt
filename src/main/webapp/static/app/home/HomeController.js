app.controller('homeController', [
    '$scope',
    'homeService',
    'localStorageService',
    '$location',
    '$rootScope',
    function (
        $scope,
        homeService,
        localStorageService,
        $location,
        $rootScope) {
        $scope.listData = [];
        $rootScope.KYCModel = {};
        $scope.isUploadError = false;
        $scope.success = false;
        $scope.error = false;
        $scope.isPersonalDocUploaded = false;
        $scope.isEntityDocUploaded = false;
        $scope.isSave = false;

        $('body').removeClass('mainlogin');

        $(function () {
            $('#dateofBirth').datetimepicker({
                format: "MM/DD/YYYY",
                ignoreReadonly: true,
                maxDate: new Date()
            });
            $('#dateofBirth')
             .on("dp.change", function (e) {
                 if (e.oldDate && $scope.addEditPersonalInfo) {
                     $scope.dobRequired = false;
                 }
             });
        });

        $(function () {
            $('#idIssueDate').datetimepicker({
                format: "MM/DD/YYYY",
                ignoreReadonly: true,
                maxDate: new Date()
            });
            $('#idIssueDate')
                .on("dp.change", function (e) {
                    if (e.oldDate && $scope.addEditPersonalInfo) {
                        $scope.idIssueDateRequired = false;
                    }
                });
        });

        $(function () {
            $('#idExpiryDate').datetimepicker({
                format: "MM/DD/YYYY",
                ignoreReadonly: true
            });
            $('#idExpiryDate')
                .on("dp.change", function (e) {
                    if (e.oldDate && $scope.addEditPersonalInfo) {
                        $scope.expiryDateRequired = false;
                    }
                });
        });

        function init() {
            if (localStorageService.get('userName') == undefined || localStorageService.get('userName') == null) {
                $location.path('/login')
            }
            getInitialData();

            $scope.states = [];
            $rootScope.KYCModel.personalInfoModel = {};
            $rootScope.KYCModel.entityInfoModel = {};
            $rootScope.KYCModel.accountInfoModel = {};
            $rootScope.docArray = [];
            $rootScope.docEntityArray = [];
            $rootScope.docEDDArray = [];
            $scope.isFileSelected = false;
            $scope.isEDDFileSelected = false;
            $scope.docTypeRequired = false;
            $scope.idTypeRequired = false;
            $scope.issueDateRequired = false;
            $scope.idNoeRequired = false;
            $scope.expiryDateRequired = false;
            $scope.countryofissueRequired = false;
            $scope.entityDocUpload = false;
            $scope.entityEDDUpload = false;
            $scope.dateCompare = false;
            $scope.emailCompare = false;
            $scope.invalidEmail = false;
            $scope.invalidCallbackEmail = false;
            $scope.dobRequired = false;
            $scope.idIssueDateRequired = false;
            $scope.expiryDateRequired = false;
            $scope.strError="";
            $scope.isServerErrors=false;
        }

        $scope.selectedTab = 'personal';

        $scope.selectTab = function (tab) {
            $scope.selectedTab = tab;
        }

        $scope.savePersonalInfo = function () {
            $scope.addEditPersonalInfo.$setSubmitted(true);
            if ($("#dateofBirth").data('date') == undefined || $("#dateofBirth").data('date') == null) {
                $scope.dobRequired = true;
                $scope.error = true;
                return false;
            }
            if ($scope.addEditPersonalInfo.$valid) {
                $rootScope.KYCModel.personalInfoModel.type = "PersonalInfo";//change
                if ($rootScope.docArray.length == 0) {
                    $scope.isPersonalDocUploaded = true;
                    $scope.docTypeRequired = true;
                    $scope.idTypeRequired = true;
                    $scope.countryofissueRequired = true;
                    $scope.idNoeRequired = true;
                    $scope.idIssueDateRequired = true;
                    $scope.expiryDateRequired = true;
                    return false;
                }
                $rootScope.KYCModel.personalInfoModel.userName = localStorageService.get('userName');
                $rootScope.KYCModel.personalInfoModel.dob = $("#dateofBirth").data('date');
                $rootScope.KYCModel.personalInfoModel.idIssueDate = $("#idIssueDate").data('date');
                $rootScope.KYCModel.personalInfoModel.idExpiryDate = $("#idExpiryDate").data('date');
                var isValid = true;
                isvalid = validateEmail($rootScope.KYCModel.personalInfoModel.email, $rootScope.KYCModel.personalInfoModel.alternateEmail)
                if (!isvalid)
                    return false;
                if ($rootScope.KYCModel.personalInfoModel.country) {
                    $rootScope.KYCModel.personalInfoModel.countryName = $rootScope.KYCModel.personalInfoModel.country.name;
                }
                $rootScope.KYCModel.personalInfoModel.documentUploadDetail = $rootScope.docArray;//change $rootScope.docArray
                if ($scope.file != undefined) {
                    var fileArray = $scope.file.split('.');
                    $rootScope.KYCModel.personalInfoModel.file = fileArray[0] + "_" + $rootScope.KYCModel.personalInfoModel.userName + "." + fileArray[1];
                }
                homeService.addPersonalInfo($rootScope.KYCModel.personalInfoModel).then(function (results) {
                    if (results.statusText == "OK") {
                        $scope.isSave = true;
                        $scope.success = true;
                        $scope.error = false;
                    } else {
               
                    	showServerErrors(results.data);
                        $scope.error = true;
                        $scope.success = false;
                    }
                }, function (error) {
                   
                });
            } else {
                $scope.error = true;
            }
        };

        $scope.saveEntityInfo = function () {
            $scope.addEditEntityInfo.$setSubmitted(true);
            if ($scope.addEditEntityInfo.$valid) {
                $rootScope.KYCModel.entityInfoModel.type = "EntityInfo";
                if ($scope.docEDDArray.length == 0 || $rootScope.docEntityArray.length == 0) {
                    $scope.isEntityDocUploaded = true;
                    $scope.entityDocUpload = true;
                    $scope.entityEDDUpload = true;
                    return false;
                }

                $rootScope.KYCModel.entityInfoModel.userName = localStorageService.get('userName');
                if ($rootScope.KYCModel.entityInfoModel.hqCountry) {
                    $rootScope.KYCModel.entityInfoModel.hqCountryName = $rootScope.KYCModel.entityInfoModel.hqCountry.name;
                }
                if ($rootScope.KYCModel.entityInfoModel.mailingCountry) {
                    $rootScope.KYCModel.entityInfoModel.mailingCountryName = $rootScope.KYCModel.entityInfoModel.mailingCountry.name;
                }
                if ($scope.file != undefined) {
                    var fileArray = $scope.file.split('.');
                    $rootScope.KYCModel.entityInfoModel.file = fileArray[0] + "_" + $rootScope.KYCModel.entityInfoModel.userName + "." + fileArray[1];
                }
                if ($scope.fileEDD != undefined) {
                    var fileArray = $scope.fileEDD.split('.');
                    $rootScope.KYCModel.entityInfoModel.eddFile = fileArray[0] + "_" + $rootScope.KYCModel.entityInfoModel.userName + "." + fileArray[1];
                }
                $rootScope.KYCModel.entityInfoModel.entityDocUpload = $rootScope.docEntityArray;//change
                $rootScope.KYCModel.entityInfoModel.enhanceDueDiligence = $rootScope.docEDDArray;//change
                homeService.addEntityInfo($rootScope.KYCModel.entityInfoModel).then(function (results) {
                    if (results.statusText == "OK") {
                        $scope.isSave = true;
                        $scope.success = true;
                        $scope.error = false;
                    } else {
                        $scope.error = true;
                        $scope.success = false;
                    }
                }, function (error) {

                });
            } else {
                $scope.error = true;
            }
        };

        $scope.saveAccountInfo = function () {
            $scope.addEditAccountInfo.$setSubmitted(true);
            if ($scope.addEditAccountInfo.$valid) {
                $rootScope.KYCModel.accountInfoModel.type = "AccountInfo";
                $rootScope.KYCModel.accountInfoModel.userName = localStorageService.get('userName');
                homeService.addAccountInfo($rootScope.KYCModel.accountInfoModel).then(function (results) {
                    if (results.statusText == "OK") {
                        $('#myModal').modal('show');
                        $scope.success = true;
                        $scope.error = false;
                    } else {
                        $scope.error = true;
                        $scope.success = false;
                    }
                }, function (error) {

                });
                if ($rootScope.KYCModel.personalInfoModel.country) {
                    $rootScope.KYCModel.personalInfoModel.countryName = $rootScope.KYCModel.personalInfoModel.country.name;
                }
                homeService.addPersonalInfo($rootScope.KYCModel.personalInfoModel).then(function (results) {
                    if (results.statusText == "OK") { }
                   
                }, function (error) {
                   
                });
                if ($rootScope.KYCModel.entityInfoModel.hqCountry) {
                    $rootScope.KYCModel.entityInfoModel.hqCountryName = $rootScope.KYCModel.entityInfoModel.hqCountry.name;
                }
                if ($rootScope.KYCModel.entityInfoModel.mailingCountry) {
                    $rootScope.KYCModel.entityInfoModel.mailingCountryName = $rootScope.KYCModel.entityInfoModel.mailingCountry.name;
                }
                homeService.addEntityInfo($rootScope.KYCModel.entityInfoModel).then(function (results) {
                    if (results.statusText == "OK") { }
                      
                }, function (error) {

                });
            } else {
                $scope.error = true;
            }
        };
        function showServerErrors(errorList){
        	angular.forEach(errorList, function (d, i) {
                if($scope.strError===""){
             	   $scope.strError="<ul><li>"+d[i]+"</li>";
                }else{
             	   $scope.strError=$scope.strError + "<li>"+d[i]+"</li>";
                }
             });
         	$scope.strError=$scope.strError +"</ul>";
         	 $scope.isServerErrors=true
        }

        $scope.validateForm = function (e) {
            var isdaterror = validateDate();
            if (!$rootScope.KYCModel.personalInfoModel.documentType
                || !$rootScope.KYCModel.personalInfoModel.idType
                || !$rootScope.KYCModel.personalInfoModel.idNo
                || !$rootScope.KYCModel.personalInfoModel.idCountry || !isdaterror) {
                $scope.docTypeRequired = true;
                $scope.idTypeRequired = true;
                $scope.idNoeRequired = true;
                $scope.countryofissueRequired = true;
                $scope.idIssueDateRequired = true;
                $scope.expiryDateRequired = true;
                e.preventDefault();
                $scope.error = true;
            } else {
                $scope.docTypeRequired = false;
                $scope.idTypeRequired = false;
                $scope.idNoeRequired = false;
                $scope.countryofissueRequired = false;
                $scope.idIssueDateRequired = false;
                $scope.expiryDateRequired = false;
                $scope.error = false;
            }
        }

        $scope.selectFile = function (file) {
            var isDuplicate = false;
            var fd = new FormData();
            var data = file[0];
            var ext = data.name.split('.').pop().toLowerCase();
            if (!($.inArray(ext, ['pdf', 'png', 'jpeg', 'jpg']) == -1)) {
                $("#fileError").css("display", "none");
                $scope.isFileSelected = true;
                var docObject = {
                    dtype: $rootScope.KYCModel.personalInfoModel.idType,
                    documentType: $rootScope.KYCModel.personalInfoModel.documentType,
                    idNo: $rootScope.KYCModel.personalInfoModel.idNo,
                    idCountry: $rootScope.KYCModel.personalInfoModel.idCountry,
                    idIssueDate: $("#idIssueDate").data('date'),
                    idExpiryDate: $("#idExpiryDate").data('date'),
                    orignalName: data.name,
                    newFileName: $rootScope.KYCModel.personalInfoModel.idType + "_" + localStorageService.get('userName') + "." + data.name.split('.')[1]
                };
                angular.forEach($rootScope.docArray, function (d, i) {
                    if (d.dtype === $rootScope.KYCModel.personalInfoModel.idType) {
                        $('#modalConfirmYesNo').modal('show');
                        $("#btnYes").off('click').click(function () {
                            $('#modalConfirmYesNo').modal('hide');
                            $rootScope.docArray[i] = docObject;
                            uploadFile(file, fd, data);
                        });
                        $("#btnNo").off('click').click(function () {
                            $('#modalConfirmYesNo').modal('hide');
                        });
                        isDuplicate = true;
                    }
                });
                if (!isDuplicate) {
                    $rootScope.docArray.push(docObject);
                    uploadFile(file, fd, data);
                }
            } else {
                $("#fileError").text("Error! Only PDF, JPEG or PNG files are allowed.");
                $("#fileError").css("display", "block");
            }
        };

        function uploadFile(file, fd, data) {
            $scope.file = data.name;
            fd.append("file", data);
            fd.append("userName", localStorageService.get('userName'));
            fd.append("newFileName", $rootScope.KYCModel.personalInfoModel.idType + "_" + localStorageService.get('userName') + "." + data.name.split('.')[1]);
            homeService.uploadFile(fd).then(function (results) {
                $scope.isPersonalDocUploaded = false;
                $rootScope.KYCModel.personalInfoModel.idType = "";
                $rootScope.KYCModel.personalInfoModel.documentType = "";
                $rootScope.KYCModel.personalInfoModel.idNo = "";
                $rootScope.KYCModel.personalInfoModel.idCountry = "";
                $rootScope.KYCModel.personalInfoModel.idIssueDate = "";
                $rootScope.KYCModel.personalInfoModel.idExpiryDate = "";
                $('#idIssueDate').data('DateTimePicker').date(null);
                $('#idExpiryDate').data('DateTimePicker').date(null);
            }, function (error) {

            });

        }

        $scope.validateEntityForm = function (e) {
            if (!$rootScope.KYCModel.entityInfoModel.entityDocUpload) {
                $scope.entityDocUpload = true;
                e.preventDefault();
                $scope.error = true;
            } else {
                $scope.entityDocUpload = false;
                $scope.error = false;
            }
        }

        $scope.selectEntityFile = function (file) {
            var isDuplicate = false
            var fd = new FormData();
            var data = file[0];
            var ext = data.name.split('.').pop().toLowerCase();
            if (!($.inArray(ext, ['pdf', 'png', 'jpeg', 'jpg']) == -1)) {
                $("#entityFileError").css("display", "none");
                $scope.isFileSelected = true;
                var docObject = {
                    documentType: $rootScope.KYCModel.entityInfoModel.entityDocUpload,
                    orignalName: data.name,
                    newFileName: $rootScope.KYCModel.entityInfoModel.entityDocUpload + "_" + localStorageService.get('userName') + "." + data.name.split('.')[1]
                };
                var cnt = 0;
                angular.forEach($rootScope.docEntityArray, function (d, i) {
                    if (d.documentType === docObject.documentType) {
                        $('#modalConfirmYesNo').modal('show');
                        $("#btnYes").off('click').click(function () {
                            $('#modalConfirmYesNo').modal('hide');
                            $rootScope.docEntityArray[i] = docObject;
                            uploadEntityFile(fd, data);
                        });
                        $("#btnNo").off('click').click(function () {
                            $('#modalConfirmYesNo').modal('hide');
                        });
                        isDuplicate = true;
                    }
                });
                if (!isDuplicate) {
                    $rootScope.docEntityArray.push(docObject);
                    uploadEntityFile(fd, data);
                }
            } else {
                $("#entityFileError").text("Error! Only PDF, JPEG or PNG files are allowed.");
                $("#entityFileError").css("display", "block");
            }
        };

        function uploadEntityFile(fd, data) {
            $scope.file = data.name;
            fd.append("file", data);
            fd.append("userName", localStorageService.get('userName'));
            fd.append("newFileName", $rootScope.KYCModel.entityInfoModel.entityDocUpload + "_" + localStorageService.get('userName') + "." + data.name.split('.')[1]);
            homeService.uploadFile(fd).then(function (results) {
                $rootScope.KYCModel.entityInfoModel.entityDocUpload = "";
            }, function (error) {

            });
        }

        $scope.validateEntityEDDForm = function (e) {
            if (!$rootScope.KYCModel.entityInfoModel.enhanceDueDiligence) {
                $scope.entityEDDUpload = true;
                e.preventDefault();
                $scope.error = true;
            } else {
                $scope.entityEDDUpload = false;
                $scope.error = false;
            }
        }

        $scope.selectEntityEDDFile = function (file) {
            var isDuplicate = false
            var fd = new FormData();
            var data = file[0];
            var ext = data.name.split('.').pop().toLowerCase();
            if (!($.inArray(ext, ['pdf', 'png', 'jpeg', 'jpg']) == -1)) {
                $("#entityFileError").css("display", "none");
                $scope.isEDDFileSelected = true;
                var docObject = {
                    documentType: $rootScope.KYCModel.entityInfoModel.enhanceDueDiligence,
                    orignalName: data.name,
                    newFileName: $rootScope.KYCModel.entityInfoModel.enhanceDueDiligence + "_" + localStorageService.get('userName') + "." + data.name.split('.')[1]
                };
                var cnt = 0;
                angular.forEach($scope.docEDDArray, function (d, i) {
                    if (d.documentType === docObject.documentType) {
                        $('#modalConfirmYesNo').modal('show');
                        $("#btnYes").off('click').click(function () {
                            $('#modalConfirmYesNo').modal('hide');
                            $rootScope.docEDDArray[i] = docObject;
                            uploadEntityEDDFile(fd, data);
                        });
                        $("#btnNo").off('click').click(function () {
                            $('#modalConfirmYesNo').modal('hide');
                        });
                        isDuplicate = true;
                    }
                });
                if (!isDuplicate) {
                    $rootScope.docEDDArray.push(docObject);
                    uploadEntityEDDFile(fd, data);
                }
            } else {
                $("#entityFileError").text("Error! Only PDF, JPEG or PNG files are allowed.");
                $("#entityFileError").css("display", "block");
            }
        };

        function uploadEntityEDDFile(fd, data) {
            $scope.fileEDD = data.name;
            fd.append("file", data);
            fd.append("userName", localStorageService.get('userName'));
            fd.append("newFileName", $rootScope.KYCModel.entityInfoModel.enhanceDueDiligence + "_" + localStorageService.get('userName') + "." + data.name.split('.')[1]);
            homeService.uploadFile(fd).then(function (results) {
                $scope.isEntityDocUploaded = false;
                $rootScope.KYCModel.entityInfoModel.enhanceDueDiligence = "";
            }, function (error) {

            });
        }

        $scope.getState = function (country) {
            $scope.states = angular.copy(country.states);
            country = country.name;
        };

        $scope.personalNext = function () {
            $('#Entity').css("display", "block");
            $('#Personal').css("display", "none")
            $scope.isSave = false;
            reset();
            pNext();
        };

        $scope.eventNext = function () {
            $('#Account').css("display", "block");
            $('#Entity').css("display", "none")
            reset();
            eNext();
        };

        $scope.eventPrev = function () {
            $('#Personal').css("display", "block");
            $('#Entity').css("display", "none")
            reset();
        };

        $scope.accountPrev = function () {
            $('#Entity').css("display", "block");
            $('#Account').css("display", "none")
            reset();
        };

        $scope.emailValidate = function (email, field) {
            if (email != "" && email != undefined) {
                var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
                var validEmail = pattern.test(email);
                if (validEmail) {
                    if (field === "email") {
                        $scope.addEditPersonalInfo.email.$setValidity($scope.addEditPersonalInfo.email.$name, true);
                        $scope.invalidEmail = false;
                    }
                    else if (field === "alternateEmail")
                        $scope.addEditPersonalInfo.alternateEmail.$setValidity($scope.addEditPersonalInfo.alternateEmail.$name, true);
                    else if (field === "entityEmail") {
                        $scope.addEditEntityInfo.email.$setValidity($scope.addEditEntityInfo.email.$name, true);
                        $scope.invalidEmail = false;
                    }
                    else if (field === "reportsEmail")
                        $scope.addEditEntityInfo.reportsEmail.$setValidity($scope.addEditEntityInfo.reportsEmail.$name, true);
                    else if (field === "callbackEmail") {
                        $scope.addEditEntityInfo.callbackEmail.$setValidity($scope.addEditEntityInfo.callbackEmail.$name, true);
                        $scope.invalidCallbackEmail = false;
                    }
                }
                else {
                    if (field === "email") {
                        $scope.addEditPersonalInfo.email.$setValidity($scope.addEditPersonalInfo.email.$name, false);
                        $scope.invalidEmail = true;
                    }
                    else if (field === "alternateEmail")
                        $scope.addEditPersonalInfo.alternateEmail.$setValidity($scope.addEditPersonalInfo.alternateEmail.$name, false);
                    else if (field === "entityEmail") {
                        $scope.addEditEntityInfo.email.$setValidity($scope.addEditEntityInfo.email.$name, false);
                        $scope.invalidEmail = true;
                    }
                    else if (field === "reportsEmail")
                        $scope.addEditEntityInfo.reportsEmail.$setValidity($scope.addEditEntityInfo.reportsEmail.$name, false);
                    else if (field === "callbackEmail") {
                        $scope.addEditEntityInfo.callbackEmail.$setValidity($scope.addEditEntityInfo.callbackEmail.$name, false);
                        $scope.invalidCallbackEmail = true;
                    }
                }
            } else {
                if (field === "email") {
                    $scope.addEditPersonalInfo.email.$setValidity($scope.addEditPersonalInfo.email.$name, true);
                    $scope.invalidEmail = false;
                }
                else if (field === "alternateEmail")
                    $scope.addEditPersonalInfo.alternateEmail.$setValidity($scope.addEditPersonalInfo.alternateEmail.$name, true);
                else if (field === "entityEmail") {
                    $scope.addEditEntityInfo.email.$setValidity($scope.addEditEntityInfo.email.$name, true);
                    $scope.invalidEmail = false;
                }
                else if (field === "reportsEmail")
                    $scope.addEditEntityInfo.reportsEmail.$setValidity($scope.addEditEntityInfo.reportsEmail.$name, true);
                else if (field === "callbackEmail") {
                    $scope.addEditEntityInfo.callbackEmail.$setValidity($scope.addEditEntityInfo.callbackEmail.$name, true);
                    $scope.invalidCallbackEmail = false;
                }
            }
        };

        $scope.validateURL = function (url) {
            if (url != "" && url != undefined) {
                var pattern = /((?:https?\:\/\/|www\.)(?:[-a-z0-9]+\.)*[-a-z0-9]+.*)/i;
                var validURL = pattern.test(url);
                if (validURL)
                    $scope.addEditEntityInfo.website.$setValidity($scope.addEditEntityInfo.website.$name, true);
                else
                    $scope.addEditEntityInfo.website.$setValidity($scope.addEditEntityInfo.website.$name, false);
            } else {
                $scope.addEditEntityInfo.website.$setValidity($scope.addEditEntityInfo.website.$name, true);
            }
        }

        $scope.resetPersonalInfo = function () {
            $scope.addEditPersonalInfo.$setPristine();
            $rootScope.KYCModel.personalInfoModel = {};
            $rootScope.docArray = [];
            $('#dateofBirth').data('DateTimePicker').date(null);
            $('#idIssueDate').data('DateTimePicker').date(null);
            $('#idExpiryDate').data('DateTimePicker').date(null);
            $scope.error = false;
            $scope.success = false;
        };

        $scope.resetEntityInfo = function () {
            $scope.addEditEntityInfo.$setPristine();
            $rootScope.KYCModel.entityInfoModel = {};
            $rootScope.docEntityArray = [];
            $rootScope.docEDDArray = [];
            $scope.error = false;
            $scope.success = false;
        };

        $scope.resetAccountInfo = function () {
            $scope.addEditAccountInfo.$setPristine();
            $rootScope.KYCModel.accountInfoModel = {};
            $scope.error = false;
            $scope.success = false;
        };

        $scope.Logout = function () {
            $('#myModal').modal('hide');
            localStorage.setItem('userName', null);
            $scope.KYCModel = {};
            $location.path('/login')
        };

        function reset() {
            window.scrollTo(0, 0);
            $scope.success = false;
            $scope.error = false;
            $scope.fileTypeError = false;
            $scope.isPersonalDocUploaded = false;
            $scope.dateCompare = false;
            $scope.emailCompare = false;
            $scope.isEntityDocUploaded = false;
        }

        function validateDate() {
            var issueDate = $("#idIssueDate").data('date');
            var expiryDate = $("#idExpiryDate").data('date');
            if (issueDate > expiryDate && (issueDate != undefined && expiryDate != undefined)) {
                $scope.dateCompare = true;
                return false;
            } else {
                $scope.dateCompare = false;
                return true;
            }
        }

        function validateEmail(email, alternateEmail) {
            if (email == alternateEmail && (email != undefined && alternateEmail != undefined)) {
                $scope.emailCompare = true;
                return false;
            } else {
                $scope.emailCompare = false;
                return true;
            }
        }

        function pNext() {
            function sticky_relocate2() {
                var window_top = $(window).scrollTop();
                var div_top = ($('#sticky-anchor2').offset() || { "top": NaN }).top;

                if (window_top > div_top) {
                    $('#sticky2').addClass('stick');

                } else {
                    $('#sticky2').removeClass('stick');
                }
            }
            $(function () {
                $(window).scroll(sticky_relocate2);
                sticky_relocate2();
            });
        }

        function eNext() {
            function sticky_relocate3() {
                var window_top = $(window).scrollTop();
                var div_top = ($('#sticky-anchor3').offset() || { "top": NaN }).top;
                if (window_top > div_top) {
                    $('#sticky3').addClass('stick');

                } else {
                    $('#sticky3').removeClass('stick');

                }
            }
            $(function () {
                $(window).scroll(sticky_relocate3);
                sticky_relocate3();
            });
        }

        $scope.setData = function (sameasAbove) {
            if (sameasAbove == true) {
                $rootScope.KYCModel.entityInfoModel.mailingAddress = angular.copy($rootScope.KYCModel.entityInfoModel.hqAddress);
                $rootScope.KYCModel.entityInfoModel.mailingStreetAddress2 = angular.copy($rootScope.KYCModel.entityInfoModel.hqStreetAddress2);
                $rootScope.KYCModel.entityInfoModel.mailingCountry = angular.copy($rootScope.KYCModel.entityInfoModel.hqCountry);
                $rootScope.KYCModel.entityInfoModel.mailingState = angular.copy($rootScope.KYCModel.entityInfoModel.hqstate);
                $rootScope.KYCModel.entityInfoModel.mailingCity = angular.copy($rootScope.KYCModel.entityInfoModel.hqCity);
                $rootScope.KYCModel.entityInfoModel.mailingZip = angular.copy($rootScope.KYCModel.entityInfoModel.hqZip);
            }
            else {
                $rootScope.KYCModel.entityInfoModel.mailingAddress = "";
                $rootScope.KYCModel.entityInfoModel.mailingStreetAddress2 = "";
                $rootScope.KYCModel.entityInfoModel.mailingCountry = "";
                $rootScope.KYCModel.entityInfoModel.mailingState = "";
                $rootScope.KYCModel.entityInfoModel.mailingCity = "";
                $rootScope.KYCModel.entityInfoModel.mailingZip = "";
            }
        };

        $('this.active')

        var acc = document.getElementsByClassName("accordion");
        var i;

        for (i = 0; i < acc.length; i++) {
            acc[i].onclick = function () {
                this.classList.toggle("active");
                this.nextElementSibling.classList.toggle("show");
            }
        }

        function getInitialData() {
            homeService.getCurrency().then(function (results) {
                if (results.data != null) {
                    $scope.listData.currencyList = results.data;
                }
            }, function (error) {

            });
            homeService.getCountries().then(function (results) {
                if (results.data != null) {
                    $scope.listData.countryList = results.data;
                }
            }, function (error) {

            });
        }

        init();
    }]);
