<template>
  <div class="main-container">
    <!-- Welcome Section -->
    <div class="welcome-section position-relative">
      <div class="overlay position-absolute top-0 start-0 w-100 h-100 bg-dark bg-opacity-50 d-flex flex-column justify-content-center align-items-center text-center">
        <h1 class="welcome-heading text-white mb-3">Welcome to Your ToDo App</h1>
        <p class="welcome-description text-white fs-4 mb-4">
          Efficiently manage tasks, track progress, and collaborate with your team effortlessly!
        </p>
      </div>
    </div>

    <!-- Features Section -->
    <div class="features-section container py-5">
      <h2 class="section-title text-center mb-5">Key Features</h2>
      <div class="row row-cols-1 row-cols-md-3 g-4">
        <!-- Feature Card 1 -->
        <div class="col">
          <div class="feature-card card shadow-lg rounded-3">
            <div class="card-body text-center">
              <i class="fas fa-tasks feature-icon mb-4 fs-2 text-primary"></i>
              <h3 class="card-title mb-3">Task Management</h3>
              <p class="card-text">Easily create, manage, and assign tasks to team members.</p>
            </div>
          </div>
        </div>

        <!-- Feature Card 2 -->
        <div class="col">
          <div class="feature-card card shadow-lg rounded-3">
            <div class="card-body text-center">
              <i class="fas fa-users feature-icon mb-4 fs-2 text-primary"></i>
              <h3 class="card-title mb-3">Team Collaboration</h3>
              <p class="card-text">Assign tasks to others, track progress, and ensure timely delivery.</p>
            </div>
          </div>
        </div>

        <!-- Feature Card 3 -->
        <div class="col">
          <div class="feature-card card shadow-lg rounded-3">
            <div class="card-body text-center">
              <i class="fas fa-calendar-check feature-icon mb-4 fs-2 text-primary"></i>
              <h3 class="card-title mb-3">Due Dates & Reminders</h3>
              <p class="card-text">Set due dates and receive automatic reminders for important tasks.</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- How It Works Section -->
    <div class="how-it-works-section bg-light py-5">
      <h2 class="section-title text-center mb-5">How It Works</h2>
      <div class="row row-cols-1 row-cols-md-3 g-4">
        <!-- Step 1 -->
        <div class="col">
          <div class="step card shadow-sm rounded-3">
            <div class="card-body text-center">
              <i class="fas fa-plus-circle step-icon mb-4 fs-2 text-primary"></i>
              <img class="step-image rounded-circle mb-3" src="/images/task2.png" alt="Create Task" />
              <p class="card-text">Create tasks and add details.</p>
            </div>
          </div>
        </div>

        <!-- Step 2 -->
        <div class="col">
          <div class="step card shadow-sm rounded-3">
            <div class="card-body text-center">
              <i class="fas fa-user-plus step-icon mb-4 fs-2 text-primary"></i>
              <img class="step-image rounded-circle mb-3" src="/images/task3.webp" alt="Assign Task" />
              <p class="card-text">Assign tasks to teammates.</p>
            </div>
          </div>
        </div>

        <!-- Step 3 -->
        <div class="col">
          <div class="step card shadow-sm rounded-3">
            <div class="card-body text-center">
              <i class="fas fa-check-circle step-icon mb-4 fs-2 text-primary"></i>
              <img class="step-image rounded-circle mb-3" src="/images/task.webp" alt="Track Progress" />
              <p class="card-text">Track progress and mark tasks as complete.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
</script>

<style scoped>
/* Global Styles */
body {
  font-family: 'Arial', sans-serif;
  background-color: #f7f9fc;
}

.main-container {
  overflow-x: hidden; /* Prevent horizontal scroll on the main container */
}
.welcome-section {
  position: relative;
  background-image: url('/images/welcome.png');
  background-size: contain;  /* Ensure the image fits within the section without cropping */
  background-position: center;
  background-repeat: no-repeat; /* Prevent the image from repeating */
  height: 40vh; /* Keeps the section height at full viewport */
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;  /* Optional: add some padding around content */
}

.welcome-heading {
  font-size: 4rem;
  font-weight: 600;
}

.welcome-description {
  font-size: 1.5rem;
  max-width: 600px;
  margin: 0 auto;
}

/* Feature Section */
.features-section .section-title {
  font-size: 2.5rem;
  font-weight: 700;
}

.feature-card {
  border: none;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.feature-card:hover {
  transform: translateY(-10px);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
}

.feature-icon {
  font-size: 3rem;
}

.card-text {
  color: #6c757d;
}

/* How It Works Section */
.how-it-works-section .section-title {
  font-size: 2.5rem;
  font-weight: 700;
}

.step {
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.step:hover {
  transform: translateY(-10px);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
}

.step-icon {
  font-size: 3rem;
}

.step-image {
  width: 120px;
  height: 120px;
  object-fit: cover;
}


/* Responsive design */
@media (max-width: 767px) {
  .welcome-heading {
    font-size: 3rem;
  }

  .welcome-description {
    font-size: 1.25rem;
  }
}
</style>
