

var Storer = {

getProperties : function(success, failure, key){
        cordova.exec(success, failure, 'StorerPlugin', 'read', [key]);
},
setProperty : function(success, failure, opts){
        cordova.exec(success, failure, 'StorerPlugin', 'write', [opts.key, opts.val]);
}
};

module.exports = Storer;