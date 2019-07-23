describe('event create test', () => {
    it('Visits the app root url', () => {
        cy.visit('/');
        cy.contains('h1', 'Welcome to Your Vue.js App');
    });
    it('visits the event create page', () => {
        cy.get('.create').click();
        cy.get('.event-create');
    });
    it('create new events', () => {
        cy.get('#name').type('test-user');
        cy.get('#date').trigger(12345678911111);
        cy.get('#duration').type(3);
        cy.get('#address').type('test adress');
        cy.get('#eventDetail').type('testetstetstete \n tetstet \n come on!');
        cy.get('#event_create--submit').click();
    });
});