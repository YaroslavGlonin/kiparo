import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(strict = false, name = "root")
data class data_news_xml (
    @field:Element(name = "location") @param:Element(name = "location") var location: String,
    @field:Element(name = "name") @param:Element(name = "name") var name:String,
    @field:ElementList(name = "element",inline = true, required = false) @param:ElementList(name = "element") @field:Path("news") var results: List<element>)
@Root(strict = false, name = "element")
data class element(
    @field:Element(name = "date") @param:Element(name = "date") var date: String,
    @field:Element(name = "description") @param:Element(name = "description") val description: String,
    @field:Element(name = "id") @param:Element(name = "id") val id: String,
    @field:ElementList(name = "keywords", entry = "keywords") @param:ElementList(name = "keywords") val keywords: List<String>,
    @field:Element(name = "title") @param:Element(name = "title") val title:String,
    @field:Element(name = "visible") @param:Element(name = "visible") val visible: Boolean)
/*
<root>
        <location>Minks</location>
        <name>Super duper news</name>
        <news>
            <element>
                <keywords>
                    <element>apple</element>
                </keywords>
            </element>
            <element>
                <date>2014-03-04 22:17:00 +0300</date>
                <description>Google today released a new extension for its Chrome browser that aims to improve Google account security for consumers and enterprise customers. The Password Alert extension discourages people from reusing their Google passwords on other sites and notifies them of potential phishing attacks. In other words, Google wants to help you help yourself.</description>
                <id>1</id>
                <keywords>
                    <element>google</element>
                </keywords>
                <title>New Chrome extension aims to protect Google passwords, foil phishers</title>
                <visible>true</visible>
            </element>
        </news>
    </root>
    data_news>
        <name>Super duper news</name>
        <location>Minks</location>
        <results>
            <results>
                <id>0</id>
                <description>An initiative between Apple, IBM and Japan Post Holdings could put iPads in the hands of up to 5 million members of Japan's elderly population.</description>
                <date>2014-10-25 12:35:00 +0300</date>
                <keywords>
                    <keywords>apple</keywords>
                    <keywords>ibm</keywords>
                </keywords>
                <visible>true</visible>
            </results>
 */