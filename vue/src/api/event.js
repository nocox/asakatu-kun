import ApiService from "./config";

const EventService = {
    createEvent(request) {
        return ApiService.post('/event/new', request);
    },
    getEventInfo(eventId) {
        return ApiService.get('/event/' + eventId);
    },
    getEventList() {
        return ApiService.get('/events');
    },
    getjoinedEventList() {
        return ApiService.get('/joinevents');
    },
    getUsers(eventId) {
        return ApiService.get('/event/' + eventId + '/users');
    },
    contract(eventId, request) {
        return ApiService.post('/event/' + eventId + '/user', request)
    }

};

export default EventService;
