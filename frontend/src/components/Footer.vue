<template>
  <footer class="footer bg-dark text-light py-2 mt-2">
    <div class="container">
      <div class="row mt-3">
        <!-- Company Info Section -->
        <div class="col-md-4 mb-4">
          <h5 class="text-white">ToDo App</h5>
          <p>Your task management solution. Stay organized and productive.</p>
        </div>

        <!-- Contact Section -->
        <div class="col-md-4 mb-4">
          <h5 class="text-white">Contact Us</h5>
          <p><i class="fas fa-phone-alt"></i> +1 234 567 890</p>
          <p><i class="fas fa-envelope"></i> support@todoapp.com</p>
          <p><i class="fas fa-map-marker-alt"></i> 123 Main Street, City, Country</p>
        </div>

        <!-- Newsletter Subscription Section -->
        <div class="col-md-4 mb-4">
          <h5 class="text-white">Subscribe to Our Newsletter</h5>
          <p>Stay updated with the latest news and updates from ToDo App.</p>
          <form @submit.prevent="subscribeNewsletter">
            <div class="input-group">
              <input type="email" v-model="email" class="form-control" placeholder="Enter your email" required />
              <button type="submit" class="btn btn-primary">Subscribe</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!-- Footer Bottom Section -->
    <div class="footer-bottom bg-dark text-light py-3">
      <div class="container text-center">
        <p>&copy; 2024 ToDo App. All Rights Reserved.</p>
      </div>
    </div>
  </footer>
</template>

<script setup>
import { ref } from 'vue';

// State for managing email input in the newsletter subscription form
const email = ref('');

// Handle the subscription logic (this is just a mock function)
const subscribeNewsletter = () => {
  if (email.value) {
    console.log(`Subscribed with email: ${email.value}`);
    // You can replace this with actual API call to subscribe
    email.value = ''; // Clear the input after subscription
  }
};
</script>

<style scoped>
/* Footer Styles */
.footer {
  background-color: #343a40;
  color: #f8f9fa;
}

.footer h5 {
  font-weight: bold;
  margin-bottom: 1rem;
}

.footer .input-group {
  max-width: 400px;
  margin: 0 auto;
}

.footer .input-group input {
  border-radius: 30px 0 0 30px;
}

.footer .input-group button {
  border-radius: 0 30px 30px 0;
  background-color: #007bff;
  color: white;
}

.footer .input-group button:hover {
  background-color: #0056b3;
}

/* Footer Bottom Section */
.footer-bottom {
  background-color: #23272b;
}

.footer-bottom a {
  color: #f8f9fa;
  text-decoration: none;
}

.footer-bottom a:hover {
  color: #007bff;
}

@media (max-width: 768px) {
  .footer .input-group {
    max-width: 100%;
  }
}
</style>
