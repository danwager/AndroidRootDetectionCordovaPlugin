var RootDetector = function () {};

RootDetector.prototype.isRooted = function(success, fail) {
	cordova.exec(success, fail, "RootDetectorPlugin", "checkForRoot", []);
};

var rootDetector = new RootDetector();
module.exports = rootDetector;