package mjg

class GeocoderService {

    String base = 'http://maps.googleapis.com/maps/api/geocode/xml?'

    def fillInLatLng(Castle castle) {
        def encodedAddress =
            [castle.city, castle.state].collect {
                URLEncoder.encode(it, 'UTF-8')
            }.join(',+')
        def url = base +
            [address: encodedAddress, sensor: false].collect { k,v ->
                "$k=$v"
            }.join('&')
        def root = new XmlSlurper().parse(url)
        castle.latitude = root.result.geometry.location.lat[0].toDouble()
        castle.longitude = root.result.geometry.location.lng[0].toDouble()
    }
}
