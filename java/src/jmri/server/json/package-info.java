/**
 * The JMRI JSON Services provide access to JMRI layout, operations and roster
 * elements.
 *
 * JSON messages in four different forms from the client are handled by the JSON
 * services:
 * <ul>
 * <li>list requests in the form:
 * <code>{"type":"list","list":"<em>type</em>"}</code> or
 * <code>{"list":"<em>type</em>"}</code>.</li>
 * <li>individual item state requests in the form:
 * <code>{"type":"<em>type</em>","data":{"name":"<em>name</em>"}}</code>. In
 * addition to the initial response, most requests will initiate listeners,
 * which will send updated responses every time the item's state changes.<ul>
 * <li>an item may be updated if object attributes are included in the message:
 * <code>{"type":"<em>type</em>","data":{"name":"<em>name</em>",...}}</code>.
 * Optionally a <strong>method</strong> node with the value <em>post</em> is
 * included in the message:
 * <code>{"type":"<em>type</em>","method":"post","data":{"name":"<em>name</em>",...}}</code>.
 * The <em>method</em> node may be included in the <em>data</em> node:
 * <code>{"type":"<em>type</em>","data":{"name":"<em>name</em>","method":"post",...}}</code>
 * Note that not all types support this.</li>
 * <li>individual types can be created if a <strong>method</strong> node with
 * the value <em>put</em> is included in message:
 * <code>{"type":"<em>type</em>","method":"put","data":{"name":"<em>name</em>"}}</code>.
 * The <em>method</em> node may be included in the <em>data</em> node:
 * <code>{"type":"turnout","data":{"name":"LT14","method":"put"}}</code> Note
 * that not all types support this.</li>
 * </ul></li>
 * <li>a heartbeat in the form <code>{"type":"ping"}</code>. The heartbeat gets
 * a <code>{"type":"pong"}</code> response.</li>
 * <li>a sign off in the form: <code>{"type":"goodbye"}</code> to which an
 * identical response is sent before the connection gets closed.</li></ul>
 *
 * JSON messages sent to the client will be in the form:
 * <ul>
 * <li><code>{"type":"<em>type</em>","data":{"name":"<em>name</em>",...}}</code></li>
 * <li><code>{"type":"pong"}</code> in response to a <code>{"type":"ping"}</code> message.</li>
 * <li>a sign off in the form: <code>{"type":"goodbye"}</code> before the connection gets closed.</li>
 * <li><code>[<em>message</em>,<em>message</em>]</code>, an array of any of the above message types.</li>
 * </ul>
 *
 * @since 4.3.4
 */
package jmri.server.json;
