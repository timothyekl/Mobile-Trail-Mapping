post '/trail/add' do
  trail = Trail.first_or_create(:name => params[:trail])
  "Added Trail #{trail.name}"
end
