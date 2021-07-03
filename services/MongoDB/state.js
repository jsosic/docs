var mystate = 'UNKNOWN';

// Note: isMaster command does not require authentication
var isMaster = db.isMaster();

if (isMaster.msg == "isdbgrid") {
    mystate = "mongos";
} else if (isMaster.ismaster && !isMaster.setName) {
    mystate = 'STANDALONE';
}

if (isMaster.setName) {
    if (isMaster.secondary) {
        mystate = 'SECONDARY';
    } else if (isMaster.arbiterOnly) {
        mystate = "ARBITER";
    } else if (isMaster.primary === isMaster.me) {
        mystate = 'PRIMARY';
    }
}

print(mystate);
