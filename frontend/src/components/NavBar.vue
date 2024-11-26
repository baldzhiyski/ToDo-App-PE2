<template>
  <nav class="navbar">
    <div class="container">
      <!-- Logo and Brand -->
      <span class="navbar-brand">
        <img src="/images/logo.png" alt="Logo" class="navbar-logo" />
        ToDo App
      </span>

      <!-- Toggler Button for mobile -->
      <button class="navbar-toggler" @click="toggleMenu">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Navbar Links -->
      <div :class="['navbar-links', { 'show': isMenuOpen }]">
        <ul>
          <!-- Home Link -->
          <li>
            <RouterLink to="/" class="nav-link">
              <font-awesome-icon icon="home" /> Home
            </RouterLink>
          </li>

          <!-- Explore Assignees Link -->
          <li>
            <router-link to="/assignees" class="nav-link">
              <font-awesome-icon icon="users" /> Explore Assignees
            </router-link>
          </li>

          <!-- ToDo List Link -->
          <li>
            <RouterLink to="/todos" class="nav-link">
              <font-awesome-icon icon="clipboard-list" /> ToDos
            </RouterLink>
          </li>
          <!-- Conditional rendering for login/logout -->
          <li v-if="!isLoggedIn">
            <a href="#" class="nav-link" @click="toggleLogin"> <font-awesome-icon icon="right-to-bracket" /> Sign In</a>
          </li>

          <li v-if="isLoggedIn">
            <a href="#" class="nav-link" @click="logout"><i class="fas fa-sign-out-alt"></i> Log Out</a>
          </li>

          <!-- More dropdown menu -->
          <li class="dropdown">
            <a href="#" class="nav-link dropdown-toggle" @click.prevent="toggleDropdown">
              <i class="fas fa-ellipsis-h"></i> More
            </a>
            <ul :class="['dropdown-menu', { 'show': isDropdownOpen }]">
              <li><a href="#" class="dropdown-item"><i class="fas fa-cog"></i> Settings</a></li>
              <li><a href="#" class="dropdown-item"><i class="fas fa-star"></i> Features</a></li>
              <li><a href="#" class="dropdown-item"><i class="fas fa-info-circle"></i> About</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue';

// Navbar state
const isMenuOpen = ref(false);
const isDropdownOpen = ref(false);
const isLoggedIn = ref(false);  // Manage user login state

// Toggle the main menu on mobile
const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value;
};

// Toggle the dropdown menu
const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value;
};

// Toggle login and signup states
const toggleLogin = () => {
  // Logic to navigate to login page or show login modal
  console.log('Login clicked');
  isLoggedIn.value = true;  // Simulate user login for testing
};

const toggleSignup = () => {
  // Logic to navigate to signup page or show signup modal
  console.log('Signup clicked');
};

const logout = () => {
  // Logic to logout the user
  console.log('Logout clicked');
  isLoggedIn.value = false;  // Simulate user logout for testing
};
</script>

<style scoped>
/* Navbar styles */
.navbar {
  background-color: #1f2937;
  padding: 1rem 1.5rem;
  color: #fff;
  position: sticky;
  top: 0;
  z-index: 1000;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.navbar-brand {
  display: flex;
  align-items: center;
  font-size: 1.5rem;
  font-weight: bold;
  color: #fff;
  text-decoration: none;
}

.navbar-logo {
  width: 40px;
  height: 40px;
  margin-right: 0.5rem;
  border-radius: 50%;
}

.navbar-toggler {
  display: none;
  background: none;
  border: none;
  cursor: pointer;
}

.navbar-toggler-icon {
  width: 25px;
  height: 2px;
  background-color: #fff;
  position: relative;
}

.navbar-toggler-icon::before,
.navbar-toggler-icon::after {
  content: '';
  width: 25px;
  height: 2px;
  background-color: #fff;
  position: absolute;
  left: 0;
}

.navbar-toggler-icon::before {
  top: -8px;
}

.navbar-toggler-icon::after {
  top: 8px;
}

.navbar-links {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  flex-wrap: wrap;
}

.navbar-links.show {
  display: block;
}

.navbar-links ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}

.navbar-links ul li {
  margin: 0 1rem;
  position: relative;
}

.navbar-links ul li a {
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.3s ease, border-bottom 0.3s ease;
}

.navbar-links ul li a:hover {
  color: #60a5fa;
  border-bottom: 2px solid #60a5fa;
}

.navbar-links ul li .dropdown-menu {
  display: none;
  position: absolute;
  background-color: #374151;
  border-radius: 0.5rem;
  list-style: none;
  padding: 0.5rem;
  top: 100%;
  left: 0;
  min-width: 10rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.navbar-links ul li .dropdown-menu.show {
  display: block;
}

.navbar-links ul li .dropdown-item {
  color: #fff;
  padding: 0.5rem;
  text-decoration: none;
  display: block;
  transition: background-color 0.3s ease;
}

.navbar-links ul li .dropdown-item:hover {
  background-color: #4b5563;
}

/* Responsive styles */
@media (max-width: 768px) {
  .navbar-toggler {
    display: block;
  }

  .navbar-links {
    display: none;
    flex-direction: column;
    width: 100%;
    text-align: center;
  }

  .navbar-links ul {
    flex-direction: column;
  }

  .navbar-links ul li {
    margin: 0.5rem 0;
  }
}
</style>
