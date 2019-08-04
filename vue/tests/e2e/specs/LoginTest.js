describe('Login test', () => {
    it('Visits the app root url', () => {
        cy.visit('/');
        cy.contains('h1', 'Welcome to Your Vue.js App');
    });
    it('visits the sign up page', () => {
        cy.get('.sign_up').click();
        cy.get('#sign-up-form');
    });
    it('can be sign up', () => {
        cy.get('#name').type('test-user');
        cy.get('#email').type('test@test.jp');
        cy.get('#password').type('testPassword');
        cy.get('#sing_in--submit').click();
    });
    it('Visits the app root url', () => {
        cy.visit('/');
        cy.contains('h1', 'Welcome to Your Vue.js App');
    });
    it('visits the login page', () => {
        cy.get('.login').click();
        cy.get('#login-form');
    });
    it('can be login', () => {
        cy.get('#name').type('test-user');
        cy.get('#password').type('testPassword');
        cy.get('#login--submit').click();
    });
});