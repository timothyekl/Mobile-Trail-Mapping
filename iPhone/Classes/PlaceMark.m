#import "PlaceMark.h"

@implementation PlaceMark

@synthesize coordinate;
@synthesize place;

-(id) initWithPlace: (Place*) place
{
	self = [super init];
	if (self != nil) {
		coordinate.latitude = place.latitude;
		coordinate.longitude = place.longitude;
		self.place = place;
	}
	return self;
}

- (NSString *)subtitle
{
	return self.place.description;
}

- (NSString *)title
{
	return self.place.name;
}

- (void) dealloc
{
	[place release];
	[super dealloc];
}


@end
