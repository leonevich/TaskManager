var m_names = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
var d_names = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

var currentTime = new Date()
var day = currentTime.getDay()
var month = currentTime.getMonth()
var date = currentTime.getDate()
var year = currentTime.getFullYear()
document.write(d_names[day] + ", " + date + " " + m_names[month] + " " + year + ", ")
var currentTime = new Date()
var hours = currentTime.getHours()
var minutes = currentTime.getMinutes()
if (minutes < 10) {
    minutes = "0" + minutes
}
document.write(hours + ":" + minutes + " ")
if (hours > 11) {
    document.write("PM")
} else {
    document.write("AM")
}