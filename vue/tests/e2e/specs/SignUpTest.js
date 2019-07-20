describe('sign up test', () => {
    it('Visits the app root url', () => {
        cy.visit('/');
        cy.contains('h1', 'Welcome to Your Vue.git js App');
    });
    it('visits the sign up page', () => {
        cy.get('.sign_up').click();
        cy.contains('#sign-up-form')
    });
    it('can be sign up', () => {
        cy.get('#name').type('test-user');
        cy.get('#email').type('test@test.jp');
        cy.get('#password').type('testPassword');
        cy.get('#sing_in--submit').click();
    });
});
