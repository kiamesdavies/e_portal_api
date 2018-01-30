
function openEventSource﻿(){
  if(getAuthorizationToken() && !alreadyOpenedEventSource ){
    var socket = $.atmosphere;
    var request = { url: andromeda_server_url+"dashboard/"+getAuthorizationToken(),
                    contentType : "application/json",
                    logLevel : 'debug',
                    transport : 'sse' ,
                    fallbackTransport: 'long-polling',
                    enableXDR : true,
                    timeout : 30 * 60 *1000,
                    shared : true
                  };

    request.onOpen = function(response) {
      //console.log(response);
    };
    request.onClientTimeout = function(response) {
      //console.log(response);
    }
    request.onMessage = function (response) {
       //console.log(response)
       var message = response.responseBody;
       try {
           var json = JSON.parse(message);
           console.log(json)
           if(json.videosShowing != undefined){
             app.mainView.$el.find("#videosShowing-val").attr("title",json.videosShowing+" video(s) showing.");
             app.mainView.$el.find("#videosShowing-val .notification").html(json.videosShowing)
             app.mainView.$el.find("#videosShowing-val").attr("data-original-title",json.videosShowing+" video(s) showing.");
             app.mainView.model.set("videosShowing", json.videosShowing)
           }
           if(json.deviceShowingVideos != undefined){
             app.mainView.$el.find("#deviceShowingVideos-val").attr("title",json.deviceShowingVideos+" showing your videos.");
             app.mainView.$el.find("#deviceShowingVideos-val .notification").html(json.deviceShowingVideos)
             app.mainView.$el.find("#deviceShowingVideos-val").attr("data-original-title",json.deviceShowingVideos+" showing your videos.");
              app.mainView.model.set("deviceShowingVideos", json.deviceShowingVideos)
           }
           if(json.allVideos != undefined){
             app.mainView.$el.find("#allVideos-val").html(json.allVideos);
             app.mainView.model.set("allVideos", json.allVideos)
           }
           if(json.allDevices != undefined){
             app.mainView.$el.find("#allDevices-val").html(json.allDevices);
             app.mainView.model.set("allDevices", json.allDevices)
           }
       } catch (e) {
           console.log('Error: ', e, message.data);
           return;
       }
   };
    socket.subscribe(request);
    alreadyOpenedEventSource = true;
  }
}

function transcend($oldContent, $newContent,callbackFunction){
    $($oldContent).fadeOut("fast",function(){
        $($newContent).fadeIn("slow",function(){
            if(typeof (callbackFunction) == "function"){
                callbackFunction();
            }
        })
    })
}

//
// $('#element').donetyping(callback[, timeout=1000])
// Fires callback when a user has finished typing. This is determined by the time elapsed
// since the last keystroke and timeout parameter or the blur event--whichever comes first.
//   @callback: function to be called when even triggers
//   @timeout:  (default=1000) timeout, in ms, to to wait before triggering event if not
//              caused by blur.
// Requires jQuery 1.7+
//
//$('#example').donetyping(function () {
//    $('#example-output').text('Event last fired @ ' + (new Date().toUTCString()));
//});
//http://stackoverflow.com/questions/14042193/how-to-trigger-an-event-in-input-text-after-i-stop-typing-writing
; (function ($) {
    $.fn.extend({
        donetyping: function (callback, timeout) {
            timeout = timeout || 1e3; // 1 second default timeout
            var timeoutReference,
                doneTyping = function (el) {
                    if (!timeoutReference) return;
                    timeoutReference = null;
                    callback.call(el);
                };
            return this.each(function (i, el) {
                var $el = $(el);
                // Chrome Fix (Use keyup over keypress to detect backspace)
                // thank you @palerdot
                $el.is(':input') && $el.on('keyup keypress', function (e) {
                    // This catches the backspace button in chrome, but also prevents
                    // the event from triggering too premptively. Without this line,
                    // using tab/shift+tab will make the focused element fire the callback.
                    if (e.type == 'keyup' && e.keyCode != 8) return;

                    // Check if timeout has been set. If it has, "reset" the clock and
                    // start over again.
                    if (timeoutReference) clearTimeout(timeoutReference);
                    timeoutReference = setTimeout(function () {
                        // if we made it here, our timeout has elapsed. Fire the
                        // callback
                        doneTyping(el);
                    }, timeout);
                }).on('blur', function () {
                    // If we can, fire the event since we're leaving the field
                    doneTyping(el);
                });
            });
        }
    });
})(jQuery);





function clearForm(form) {
    // iterate over all of the inputs for the form
    // element that was passed in
    $(':input', form).each(function () {
        var type = this.type;
        var tag = this.tagName.toLowerCase(); // normalize case
        // it's ok to reset the value attr of text inputs,
        // password inputs, and textareas
        if (type == 'text' || type == 'password' || tag == 'textarea')
            this.value = "";
            // checkboxes and radios need to have their checked state cleared
            // but should *not* have their 'value' changed
        else if (type == 'checkbox' || type == 'radio')
            this.checked = false;
            // select elements need to have their 'selectedIndex' property set to -1
            // (this works for both single and multiple select elements)
        else if (tag == 'select')
            this.selectedIndex = -1;
    });
};

//http://jquery-howto.blogspot.com/2013/08/jquery-form-reset.html
$.fn.clearForm = function () {
    return this.each(function () {
        var type = this.type, tag = this.tagName.toLowerCase();
        if (tag == 'form')
            return $(':input', this).clearForm();
        if (type == 'text' || type == 'password' || tag == 'textarea')
            this.value = '';
        else if (type == 'checkbox' || type == 'radio')
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = 1;
    });
};


//this is used like sprintf in java, replaces the occurence of a string by its index
//var temp = '<li><i class="ace-icon fa fa-square" style="color:{0} !important"></i>{1}</li>';
//temp.format("rgba(" + color + ",0.5)", output["name"])
if (!String.prototype.format) {
    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] != 'undefined'
                    ? args[number]
                    : match
            ;
        });
    };
}


//http://jsfiddle.net/sxGtM/3/
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


//http://brynner.net/html5-localStorage-with-expiration/
function removeHtmlStorage(name) {
    $.localStorage.remove(name);
    $.localStorage.remove(name+'_time');
    $.localStorage.remove(name+'_initial');
}

function setHtmlStorage(name, value, expires) {

    if (expires==undefined || expires=='null') { var expires = 3600; } // default: 1h

    var date = new Date();
    var schedule = Math.round((date.setSeconds(date.getSeconds()+expires))/1000);

    $.localStorage.set(name, value);
    $.localStorage.set(name+'_time', schedule);
    $.localStorage.set(name+'_initial', expires);
}

function  getHtmlStorage(name){
    var value = null;
     if(statusHtmlStorage(name)){
         value =  $.localStorage.get(name);
         /*var expires =  $.localStorage.get(name+'_initial');
         //reset expiration date
         setHtmlStorage(name,value,expires);*/
     }
    return value;
}

function  getResetHtmlStorage(name){
    var value = null;
    if(statusHtmlStorage(name)){
        value =  $.localStorage.get(name);
        var expires =  $.localStorage.get(name+'_initial');
        //reset expiration date
        setHtmlStorage(name,value,expires);
    }
    return value;
}


function statusHtmlStorage(name) {

    var date = new Date();
    var current = Math.round(+date/1000);

    // Get Schedule
    var stored_time = $.localStorage.get(name+'_time');
    if (stored_time==undefined || stored_time=='null') { var stored_time = 0; }

    // Expired
    if (stored_time < current) {

        // Remove
        removeHtmlStorage(name);

        return 0;

    } else {

        return 1;
    }
}

function getTodayDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
		dd='0'+dd
	} 

	if(mm<10) {
		mm='0'+mm
	} 

	return mm+'-'+dd+'-'+yyyy;

}


function getAuthorizationToken(){
    var appUser = getResetHtmlStorage(appUserStorageName);
    if(appUser != null){
        return appUser.Authorization;
    }
    return "";
}


jQuery.each( [ "put", "delete" ], function( i, method ) {
  jQuery[ method ] = function( url, data, callback, type ) {
    if ( jQuery.isFunction( data ) ) {
      type = type || callback;
      callback = data;
      data = undefined;
    }
 
    return jQuery.ajax({
      url: url,
      type: method,
      dataType: type,
      data: data,
      success: callback
    });
  };
});
