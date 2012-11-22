<%@ page import="cz.zavodprezidentu.utils.Utils; cz.zavodprezidentu.utils.Consts; cz.zavodprezidentu.utils.Colorer" %>

<g:applyLayout name="masterPage">


    <div class="box">
        <div class="box-content">
            <h1>${title}</h1>
        </div>
    </div>

    <% def colorer = new Colorer() %>
    <div class="circleStats">
        <g:each in="${accounts}" var="account" status="index">
            <% def nextColor = colorer.nextByLogoColours() %>

            <g:set var="budikId" value="budik${index}"/>

            <script type="text/javascript">
                jQuery(document).ready(function ($) {
                    $("${budikId}").click(function () {
                        window.location.href = "${account.candidate.wikiUrl}";
                    });
                });
            </script>

            <g:if test="${account.candidate.wikiUrl}">
                <a href="${account.candidate.wikiUrl}">
            </g:if>
            <div id="${budikId}" class="span2 budik"
                                 onTablet="span4"
                                 onDesktop="span2"
            <g:if test="${account.candidate.accountUrl == null}">title="Kandidát nemá zřízen transparentní účet."</g:if>>

            <g:if test="${account.candidate.accountUrl}">
                <div class="circleStatsItem ${nextColor.color}" style="background-image: url(${resource(dir: 'images/kandidati', file: account.candidate.image)});">
            </g:if>
            <g:else>
                <div class="circleStatsItem ${nextColor.color} no-data" style="background-image: url(${resource(dir: 'images/kandidati', file: account.candidate.image)});">
            </g:else>

            <!-- <i class="fa-icon-user"></i> -->
<!--                                <span class="plus">+</span>
            <span class="percent">%</span>
-->
            <input type="text" value="${Utils.getPercentage(Math.abs(account[key]), Math.abs(max))}"
                   class="${nextColor.circleColor}"/>
            </div>
            <div class="box-header">
                <h2>${account.candidate.name.replaceFirst(" ", "<br>")}</h2>
                <g:set var="formattedValue" value="${format.format(account[key]).replaceAll(" ", "&nbsp;")}"/>
                <g:if test="${account.candidate.accountUrl}">
                    <a href="${account.candidate.accountUrl}">
                        <span class="value">${formattedValue}</span>
                    </a>
                </g:if>
                <g:else>
                    <a href="http://aktualne.centrum.cz/ekonomika/vase-penize/clanek.phtml?id=761013">
                        <span class="novalue" title="Více se dozvíte, když kliknete.">nemá účet</span>
                    </a>
                </g:else>
            </div>

            </div>
            <g:if test="${account.candidate.wikiUrl}">
                </a>
            </g:if>
        </g:each>
    </div>

</g:applyLayout>