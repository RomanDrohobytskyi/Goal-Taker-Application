<#macro timeline>
  <div class="cd-schedule__timeline">
      <ul>
          <li><span>09:00</span></li>
          <li><span>09:30</span></li>
          <li><span>10:00</span></li>
          <li><span>10:30</span></li>
          <li><span>11:00</span></li>
          <li><span>11:30</span></li>
          <li><span>12:00</span></li>
          <li><span>12:30</span></li>
          <li><span>13:00</span></li>
          <li><span>13:30</span></li>
          <li><span>14:00</span></li>
          <li><span>14:30</span></li>
          <li><span>15:00</span></li>
          <li><span>15:30</span></li>
          <li><span>16:00</span></li>
          <li><span>16:30</span></li>
          <li><span>17:00</span></li>
          <li><span>17:30</span></li>
          <li><span>18:00</span></li>
      </ul>
  </div>
</#macro>

<#macro eventsForWeekend eventsWithDay>

</#macro>

<#macro dayEvents events dayName>
    <li class="cd-schedule__group">
        <div class="cd-schedule__top-info"><span>${dayName}</span></div>
        <#list events as event>
            <ul>
                <li class="cd-schedule__event">
                    <a data-start="${event.from}" data-end="${event.to}" data-content="event-abs-circuit"
                       data-event="event-1" href="#0">
                        <em class="cd-schedule__name">${event.title}</em>
                    </a>
                </li>
            </ul>
        </#list>
    </li>
</#macro>

<#macro eventDescription>
     <div class="cd-schedule-modal">
         <header class="cd-schedule-modal__header">
             <div class="cd-schedule-modal__content">
                 <span class="cd-schedule-modal__date"></span>
                 <h3 class="cd-schedule-modal__name"></h3>
             </div>
             <div class="cd-schedule-modal__header-bg"></div>
         </header>

         <div class="cd-schedule-modal__body">
             <div class="cd-schedule-modal__event-info"></div>
             <div class="cd-schedule-modal__body-bg"></div>
         </div>
         <a href="#0" class="cd-schedule-modal__close text-replace">Close</a>
     </div>

        <div class="cd-schedule__cover-layer"></div>
</#macro>