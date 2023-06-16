const wrapper = document.querySelector('.wrapper');
const signUpLink = document.querySelector('.signUp-link');
const signInLink = document.querySelector('.signIn-link');

signUpLink.addEventListener('click', () => {
    wrapper.classList.add('animate-signIn');
    wrapper.classList.remove('animate-signUp');
});

signInLink.addEventListener('click', () => {
    wrapper.classList.add('animate-signUp');
    wrapper.classList.remove('animate-signIn');
});
// New

 function toggleNavbar(collapseID) {
      document.getElementById(collapseID).classList.toggle('hidden')
      document.getElementById(collapseID).classList.toggle('block')
    }


 AOS.init({
      delay: 200,
      duration: 1200,
      once: false,
    })