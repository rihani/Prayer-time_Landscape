import cherrypy
import simplejson

class Root(object):

    @cherrypy.expose
    def update(self):
        cl = cherrypy.request.headers['Content-Length']
        rawbody = cherrypy.request.body.read(int(cl))
        body = simplejson.loads(rawbody)
        # do_something_with(body)
        return "Updated %r." % (body,)
        
    @cherrypy.expose
    @cherrypy.tools.json_out()
    @cherrypy.tools.json_in()
    def my_route(self):

        result = {"operation": "request", "result": "success"}

        input_json = cherrypy.request.json
        value = input_json["my_key"]

        #All responses are serialized to JSON. This the same as
        #return simplejson.dumps(result)
        print value
        return result
        
        

    @cherrypy.expose
    def index(self):
        return """
        
    
<html>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type='text/javascript'>



var myObject = { "my_key": "my_value" };




function Update() {
    $.ajax({
    type: "POST",
    url: "my_route",
    data: JSON.stringify(myObject),
    contentType: 'application/json',
    dataType: 'json',
    error: function() {
        alert("error");
    },
    success: function() {
        alert("you got it");
    }
});
}
</script>
<body>
<input type='textbox' id='updatebox' value='{}' size='20' />
<input type='submit' value='Update' onClick='Update(); return false' />
</body>
</html>
"""
cherrypy.config.update({'server.socket_port': 8000})
cherrypy.server.socket_host = '0.0.0.0'
cherrypy.quickstart(Root())
