import { shallowMount } from '@vue/test-utils';
import Event from '@/views/Event.vue'
import axios from 'axios';

describe('event page unit test', () => {
    it('pull the event info date', async function () {
        const eventInfo = await axios.get('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/event/' + this.eventId);
        const msg = eventInfo.data.data.startDate;
        let eventId = 1;
        const wrapper = shallowMount(Event, {
            propsData: {eventId}
        });
        expect(wrapper.text()).toMatch(msg)
    });
});