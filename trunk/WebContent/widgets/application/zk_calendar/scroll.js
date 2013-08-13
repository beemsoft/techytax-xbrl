var calenderWidget; //ZK Calendar client side widget
zk.afterMount(function() {
    calenderWidget = zk.Widget.$(jq("$calendars")); // ZK id selector
    //Mouse wheel Handle - change month
    jq('$calendars').bind(
        'mousewheel',
        function(event, delta) {
            if (calenderWidget.getMold() == "month") { //Only month mold need this
                zAu.send(new zk.Event(calenderWidget, 'onMoveViewDate',delta > 0 ? 1 : -1));
                    return false;
            }
            return true;
        }
    );
});