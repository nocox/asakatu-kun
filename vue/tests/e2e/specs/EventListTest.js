describe('event list test', () => {
    it('Visits the app root url', () => {
        cy.visit('/');
        cy.contains('h1', 'Welcome to Your Vue.js App');
    });
    it('visits the events page', () => {
        cy.get('.events').click();
        cy.get('#events');
    });
});